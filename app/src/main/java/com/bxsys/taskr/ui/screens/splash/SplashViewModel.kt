package com.bxsys.taskr.ui.screens.splash

import androidx.lifecycle.ViewModel
import com.bxsys.taskr.HOME_SCREEN
import com.bxsys.taskr.SIGN_IN_SCREEN
import com.bxsys.taskr.SPLASH_SCREEN
import com.bxsys.taskr.TaskrViewModel
import com.bxsys.taskr.model.service.api.ILogService
import com.bxsys.taskr.model.service.api.ISnackbarService
import com.bxsys.taskr.model.service.api.IUserService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

interface ISplashViewModel {
    fun onAppStart(navFromTo: (String, String) -> Unit)
}

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val userService: IUserService
): ViewModel(), ISplashViewModel {

    override fun onAppStart(navFromTo: (String, String) -> Unit) {
        if (userService.hasUser()) navFromTo(SPLASH_SCREEN, HOME_SCREEN)
        else navFromTo(SPLASH_SCREEN, SIGN_IN_SCREEN)
    }

}

class MockSplashViewModel: ISplashViewModel {
    override fun onAppStart(navFromTo: (String, String) -> Unit) {}
}