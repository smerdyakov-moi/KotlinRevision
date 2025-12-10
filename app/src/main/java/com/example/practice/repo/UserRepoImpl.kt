package com.example.practice.repo

import com.example.practice.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.FirebaseDatabaseKtxRegistrar
import com.google.firebase.database.ValueEventListener

class UserRepoImpl : UserRepo {

    val auth: FirebaseAuth = FirebaseAuth.getInstance() // auth is exclusively used for user purpose
    val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    val userRef: DatabaseReference = database.getReference("users")
    //val productRef: DatabaseReference = database.getReference("products")


    override fun login(
        email: String,
        password: String,
        callback: (Boolean, String) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
            if(it.isSuccessful){
                callback(true,"Login Successful")
            }else{
                callback(false,it.exception?.message.toString())
            }
        }
    }

    override fun register(
        email: String,
        password: String,
        callback: (Boolean, String, String) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
            if(it.isSuccessful){
                callback(true,"Registration Successful","${auth.currentUser?.uid}")
            }else{
                callback(false,it.exception?.message.toString(),"")
            }
        }
    }

    override fun forgetPassword(
        email: String,
        callback: (Boolean, String) -> Unit
    ) {
        auth.sendPasswordResetEmail(email).addOnCompleteListener {
            if(it.isSuccessful){
                callback(true,"Email sent")
            }else{
                callback(false,it.exception?.message.toString())
            }
        }
    }

    override fun updateProfile(
        userId: String,
        model: UserModel,
        callback: (Boolean, String) -> Unit
    ) {
        userRef.child(userId).updateChildren(model.toMap()).addOnCompleteListener {
            if (it.isSuccessful){
                callback(true,"User updated successfully!")
            }else{
                callback(false,it.exception?.message.toString())
            }
        }
    }

    override fun logout(callback: (Boolean, String) -> Unit) {
        try{
            auth.signOut()
            callback(true,"Logged out successfully!")
        }catch (e: Exception){
            callback(false, e.message.toString())
        }
    }

    override fun getUserById(
        userId: String,
        callback: (Boolean, String, UserModel?) -> Unit
    ) {
        userRef.child(userId).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    val users = snapshot.getValue(UserModel::class.java)
                    if(users != null){
                        callback(true,"User fetched successfully!",users)
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
                    callback(false,error.message,null)
            }
        })
    }

    override fun getAllUsers(callback: (Boolean, String, List<UserModel>) -> Unit) {
        userRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    val allUsers = mutableListOf<UserModel>()
                    for(user in snapshot.children){
                        val users = user.getValue(UserModel::class.java)
                        if (users != null) {
                            allUsers.add(users)
                        }
                    }
                    callback(true,"Fetched",allUsers)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    override fun deleteAccount(
        userId: String,
        callback: (Boolean, String) -> Unit
    ) {
        userRef.child(userId).removeValue().addOnCompleteListener {
            if (it.isSuccessful){
                callback(true,"User deleted successfully!")
            }else{
                callback(false,it.exception?.message.toString())
            }
        }
    }

    override fun getCurrentUser():FirebaseUser? {
        return auth.currentUser
    }

    override fun addUserToDatabase(
        userId: String,
        model: UserModel,
        callback: (Boolean, String) -> Unit
    ){
        userRef.child(userId).setValue(model).addOnCompleteListener {
            if(it.isSuccessful){
                callback(true,"User Added")
            }else{
                callback(false,it.exception?.message.toString())
            }
        }
    }
}
