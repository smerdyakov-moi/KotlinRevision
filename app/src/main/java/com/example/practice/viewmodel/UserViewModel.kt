package com.example.practice.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practice.model.UserModel
import com.example.practice.repo.UserRepo
import com.google.firebase.auth.FirebaseUser

class UserViewModel(val repo: UserRepo) : ViewModel() {

    private val _user = MutableLiveData<UserModel?>()
    private val _allUsers = MutableLiveData<List<UserModel>?>()

    // Getters
    val user : MutableLiveData<UserModel?>
        get () = _user
    val allUsers : MutableLiveData<List<UserModel>?>
        get() = _allUsers

    private val _loading = MutableLiveData<Boolean> ()
    val loading : MutableLiveData<Boolean>
        get() = _loading

    fun login(email:String, password:String,callback: (Boolean,String) -> Unit){
        repo.login(email,password,callback)
    }
    fun register(email: String, password: String,callback: (Boolean,String,String) -> Unit){
        repo.register(email,password,callback)
    }
    fun forgetPassword(email: String,callback: (Boolean,String) -> Unit){
        repo.forgetPassword(email,callback)
    }
    fun updateProfile(userId: String, model: UserModel,callback:(Boolean,String) -> Unit){
        repo.updateProfile(userId,model,callback)
    }
    fun logout(callback: (Boolean,String) -> Unit){
        repo.logout(callback)
    }
    fun getUserById(userId: String){

    }
    fun getAllUsers(){

    }
    fun deleteAccount(userId: String, callback: (Boolean, String) -> Unit){
        repo.deleteAccount(userId,callback)
    }
    fun getCurrentUser() : FirebaseUser?{
        return repo.getCurrentUser()
    }
    fun addUserToDatabase(userId: String, model: UserModel, callback: (Boolean, String) -> Unit){
        repo.addUserToDatabase(userId,model,callback)
    }
}