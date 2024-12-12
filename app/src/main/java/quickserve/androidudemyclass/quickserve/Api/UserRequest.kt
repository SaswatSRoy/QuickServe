package quickserve.androidudemyclass.quickserve.Api

data class UserRequest(
    val name: String,
    val email: String,
    val password: String,
    val contactNumber: String
)
data class UserResponse(val success: Boolean, val message: String, val token: String?)

data class LoginRequest(val email: String, val password: String)
data class LoginResponse(val success: Boolean, val message: String, val token: String?)

data class OtpRequest(val email: String, val otp: String)
data class OtpResponse(val success: Boolean, val message: String)

data class CompleteSignupRequest(val address: String, val additionalDetails: String)
data class CompleteSignupResponse(val success: Boolean, val message: String)
