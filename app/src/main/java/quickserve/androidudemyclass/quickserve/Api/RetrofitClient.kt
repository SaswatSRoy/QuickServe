package quickserve.androidudemyclass.quickserve.Api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    // You can dynamically change the base URL based on the environment (Emulator or Device)
    private const val BASE_URL_EMULATOR = "http://10.0.2.2:5000/api/auth/"   // For Emulator
    private const val BASE_URL_DEVICE = "http://192.168.0.103:5000/api/auth/" // For Physical Device

    // Function to determine if the device is an emulator


    // Create Retrofit instance with the selected BASE_URL
    val instance: Api by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL_EMULATOR) // Dynamically set base URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }
}
