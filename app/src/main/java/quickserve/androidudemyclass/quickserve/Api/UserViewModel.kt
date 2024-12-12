package quickserve.androidudemyclass.quickserve.Api

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun signUp(userRequest: UserRequest, onResult: (Result<UserResponse>) -> Unit) {
        viewModelScope.launch {
            val result = userRepository.signUp(userRequest)
            onResult(result)
        }
    }

    fun login(loginRequest: LoginRequest, onResult: (Result<LoginResponse>) -> Unit) {
        viewModelScope.launch {
            val result = userRepository.login(loginRequest)
            onResult(result)
        }
    }
}

class UserViewModelFactory(private val userRepository: UserRepository) : ViewModelProvider.Factory {
    // Override the create() method to instantiate the ViewModel
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Ensure that the factory is only used for UserViewModel
        return if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            UserViewModel(userRepository) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}