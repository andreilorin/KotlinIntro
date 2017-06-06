package sparKotlin

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import spark.Request
import spark.Spark.*
import spark.kotlin.*

fun main(args: Array<String>) {

    val http: Http = ignite()

    val customerDao = CustomerDao()

    exception(Exception::class.java) { e, req, res -> e.printStackTrace() }

    http.get("/hello") {
        "Hello"
    }

    path("/users") {

        get("") { req, res ->
            jacksonObjectMapper().writeValueAsString(customerDao.users)
        }

        get("/:id") { req, res ->
            customerDao.findById(req.params("id").toInt())
        }

        get("/email/:email") { req, res ->
            customerDao.findByEmail(req.params("email"))
        }

        post("/create") { req, res ->
            customerDao.save(name = req.qp("name"), email = req.qp("email"))
            res.status(201)
            "ok"
        }

        patch("/update/:id") { req, res ->
            customerDao.update(
                    id = req.params("id").toInt(),
                    name = req.qp("name"),
                    email = req.qp("email")
            )
            "ok"
        }

        delete("/delete/:id") { req, res ->
            customerDao.delete(req.params("id").toInt())
            "ok"
        }

    }

    customerDao.users.forEach(::println)

}

fun Request.qp(key: String): String = this.queryParams(key)