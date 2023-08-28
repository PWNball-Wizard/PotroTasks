package com.potro.potrotasks.navigation

sealed class AppScreens (val route:String){
    object SplashScreen : AppScreens("splash_screen")
    object SignIn : AppScreens("signin_screen")
    object MainActivity : AppScreens("main_screen")
    /*object AddTaskScreen : AppScreens("add_task_screen")
    object TaskDetailScreen : AppScreens("task_detail_screen")
    object EditTaskScreen : AppScreens("edit_task_screen")
    object LocationScreen : AppScreens("location_screen")
    object ProfileScreen : AppScreens("profile_screen")
    object EditProfileScreen : AppScreens("edit_profile_screen")
    object AboutScreen : AppScreens("about_screen")
    object HelpScreen : AppScreens("help_screen")*/
}