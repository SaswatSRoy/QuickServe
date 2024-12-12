package quickserve.androidudemyclass.quickserve.Api

import android.telecom.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface Api {
    @POST("signup")
    suspend fun signUp(@Body user: UserRequest): Response<UserResponse>

    @POST("login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @POST("verify-otp")
    suspend fun verifyOTP(@Body otpRequest: OtpRequest): Response<OtpResponse>

    @POST("complete-signup")
    suspend fun completeSignup(
        @Header("Authorization") token: String,
        @Body details: CompleteSignupRequest
    ): Response<CompleteSignupResponse>

    @GET("google")
    suspend fun googleSignIn(): Response<Void>
}