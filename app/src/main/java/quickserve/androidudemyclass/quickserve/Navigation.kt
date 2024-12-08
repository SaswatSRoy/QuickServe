package quickserve.androidudemyclass.quickserve

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import quickserve.androidudemyclass.quickserve.screens.ScreensForTheApp
import quickserve.androidudemyclass.quickserve.ui.LoginAndSignUp.LoginScreen
import quickserve.androidudemyclass.quickserve.ui.LoginAndSignUp.SignUp
import quickserve.androidudemyclass.quickserve.ui.LoginAndSignUp.Verify
import quickserve.androidudemyclass.quickserve.ui.introScreens.FinalScreen
import quickserve.androidudemyclass.quickserve.ui.introScreens.FirstScreen
import quickserve.androidudemyclass.quickserve.ui.introScreens.SecondScreen
import quickserve.androidudemyclass.quickserve.ui.introScreens.ThirdScreen
import quickserve.androidudemyclass.quickserve.welcome.HomeScreen

@Composable
fun Navigation(
    navController: NavHostController
){

    NavHost(
        navController = navController,
        startDestination = ScreensForTheApp.WelcomeScreen.route
    ) {
        composable(
            route = ScreensForTheApp.WelcomeScreen.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(300)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(300)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(300)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(300)
                )
            }
        ) {
            HomeScreen(

                navHostController = navController,
                currentPage = 0
            )
        }
        composable(
            route = ScreensForTheApp.FirstScreen.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(300)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(300)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(300)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(300)
                )
            }
        ) {
            FirstScreen(
                onNavigateTo = {
                    navController.navigate(ScreensForTheApp.SecondScreen.route)
                },
                navHostController = navController,
                currentPage = 0
            )
        }
        composable(
            route = ScreensForTheApp.SecondScreen.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(300)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(300)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(300)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(300)
                )
            }
        ){
            SecondScreen(onNavigateTo = {
                navController.navigate(ScreensForTheApp.ThirdScreen.route){
                    launchSingleTop=true
                    restoreState=true
                }
            },
                navHostController = navController,
                currentPage = 1
            )
        }
        composable(
            route = ScreensForTheApp.ThirdScreen.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(300)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(300)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(300)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(300)
                )
            }

        ){
            ThirdScreen(onNavigateTo = {
                navController.navigate(ScreensForTheApp.FinalScreen.route){
                    launchSingleTop=true
                    restoreState=true
                }
            },
                navHostController =navController,
                currentPage = 2)

        }
        composable(
            route = ScreensForTheApp.FinalScreen.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(300)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(300)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(300)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(300)
                )
            }
        ){
            FinalScreen(onNavigateTo = {
                navController.navigate(ScreensForTheApp.LoginScreen.route){
                    launchSingleTop=true
                    restoreState=true
                }
            }, navHostController = navController,
                currentPage = 3
            )
        }
        composable(
            route = ScreensForTheApp.LoginScreen.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(300)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(300)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(300)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(300)
                )
            }
        ){
            LoginScreen(onNavigateTo = {
                navController.navigate(ScreensForTheApp.SignUpScreen.route){
                    launchSingleTop=true
                    restoreState=true
                }
            }, navHostController = navController,
                currentPage = 4
            )
        }
        composable(
            route = ScreensForTheApp.SignUpScreen.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(300)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(300)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(300)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(300)
                )
            }
        ){
            SignUp(onNavigateTo = {
                navController.navigate(ScreensForTheApp.VerificationScreen.route){
                    launchSingleTop=true
                    restoreState=true
                }
            }, navHostController = navController,
                currentPage = 5
            )
        }
        composable(
            route = ScreensForTheApp.VerificationScreen.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(300)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(300)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(300)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(300)
                )
            }
        ){
            Verify(onNavigateTo = {
                navController.navigate(ScreensForTheApp.VerificationScreen.route){
                    launchSingleTop=true
                    restoreState=true
                }
            }, navHostController = navController,
                currentPage = 5
            )
        }


    }
}


