package dao

import data.Customer
import java.util.concurrent.atomic.AtomicInteger

class CustomerDao {

    val customers = hashMapOf(
            0 to Customer(name = "Andrei", email = "andrei@mail.com", id = 0),
            1 to Customer(name = "Lorin", email = "lorin@mail.com", id= 1),
            2 to Customer(name = "Bianca", email = "bianca@email.com", id = 2)
    )

    var lastId: AtomicInteger = AtomicInteger(customers.size - 1)

    fun save(name: String, email: String) {
        val id = lastId.incrementAndGet()
        customers.put(id, Customer(name = name, email = email, id = id))
    }

    fun findById(id: Int): Customer? {
        return customers[id]
    }

    fun findByEmail(email: String): Customer? {
        return customers.values.find { it.email == email }
    }

    fun update(id: Int, name: String, email: String) {
        customers.put(id, Customer(name = name, email = email, id = id))
    }

    fun delete(id: Int) {
        customers.remove(id)
    }
}