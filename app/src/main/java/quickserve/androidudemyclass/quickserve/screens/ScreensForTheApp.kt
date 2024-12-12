package quickserve.androidudemyclass.quickserve.screens

sealed class ScreensForTheApp(val route: String) {
    data object FirstScreen : ScreensForTheApp("first_screen")
    data object SecondScreen : ScreensForTheApp("second_screen")
    data object ThirdScreen : ScreensForTheApp("third_screen")
    data object FinalScreen : ScreensForTheApp("final_screen")
    data object WelcomeScreen: ScreensForTheApp("welcome_screen")
    data object LoginScreen:ScreensForTheApp("login_screen")
    data object SignUpScreen:ScreensForTheApp("sign_up_screen")
    data object VerificationScreen:ScreensForTheApp("verify")
    data object Location:ScreensForTheApp("location")
    data object ProfileEdit:ScreensForTheApp("profile_edit")
    data object MainProfile:ScreensForTheApp("profile_main")
    data object EditAddress:ScreensForTheApp("edit_address")
    data object NewAddress:ScreensForTheApp("new_address")
    data object HomeScreen:ScreensForTheApp("home_screen")

}