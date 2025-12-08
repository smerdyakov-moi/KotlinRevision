package com.example.practice.model

data class UserModel(
    val email: String,
    val firstName: String ="",
    val lastName: String ="",
    val password:String ="",
    val dob: String = "",
    val gender:String="",
    val phone:Int=0
)
