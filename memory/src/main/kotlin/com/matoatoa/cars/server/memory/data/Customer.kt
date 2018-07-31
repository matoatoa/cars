package com.matoatoa.cars.server.memory.data

data class Customer(val id: Int = -1, val firstName: String = "", val lastName: String = "", val address: String = ""){
    fun withLastName(lastName : String) = Customer(this.id,this.firstName,lastName,this.address)
}