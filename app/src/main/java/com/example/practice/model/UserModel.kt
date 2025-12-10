package com.example.practice.model

data class UserModel(
    val email: String,
    val firstName: String ="",
    val lastName: String ="",
    val password:String ="",
    val dob: String = "",
    val gender:String="",
    val phone:String="",
    val userId:String=""
){
    fun toMap(): Map<String, Any>{
        return mapOf(
            "email" to email,
            "firstName" to firstName,
            "lastName" to lastName,
            "password" to password,
            "dob" to password,
            "gender" to gender,
            "phone" to phone,
            "userId" to userId
        )
    }
}
