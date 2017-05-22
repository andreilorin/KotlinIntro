package sparKotlin

import spark.Spark.*

fun main(args: Array<String>) {
    get("/frontpage") { req, res -> "Welcome to andreilorin"}
}
