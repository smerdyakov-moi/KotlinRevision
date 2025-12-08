package com.example.practice.repo

import com.example.practice.model.UserModel

interface UserRepo {

    fun login(email:String, password:String,callback: (Boolean,String) -> Unit)
    fun register(email: String, password: String,callback: (Boolean,String,String) -> Unit)
    fun forgetPassword(email: String,callback: (Boolean,String) -> Unit)
    fun updateProfile(userId: String, model: UserModel,callback:(Boolean,String) -> Unit)
    fun logout(callback: (Boolean,String) -> Unit)
    fun getUserById(userId: String, callback: (Boolean, String, UserModel?) -> Unit )
    fun getAllUsers(callback: (Boolean, String, List<UserModel>) -> Unit)

    fun addUserToDatabase(userId: String, model: UserModel, callback: (Boolean, String) -> Unit)
}

interface ProductRepo {
    fun addProduct()
    fun updateProduct()
    fun deleteProduct()
    fun getProductById()
    fun getAllProducts()
}