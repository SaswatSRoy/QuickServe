package quickserve.androidudemyclass.quickserve.Api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository(private val api: Api) {

    suspend fun signUp(userRequest: UserRequest): Result<UserResponse> {
        return try {
            val response = api.signUp(userRequest)  // API call
            if (response.isSuccessful) {
                Result.Success(response.body()!!)  // Return success
            } else {
                Result.Error(Exception("API Error: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    suspend fun login(loginRequest: LoginRequest): Result<LoginResponse> {
        return try {
            val response = api.login(loginRequest)  // API call
            if (response.isSuccessful) {
                Result.Success(response.body()!!)  // Return success
            } else {
                Result.Error(Exception("API Error: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}