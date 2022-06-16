package com.bxsys.taskr.ui.screens.signin

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.bxsys.taskr.HOME_SCREEN
import com.bxsys.taskr.SIGN_IN_SCREEN
import com.bxsys.taskr.SIGN_UP_SCREEN
import com.bxsys.taskr.TaskrViewModel
import com.bxsys.taskr.model.service.api.ILogService
import com.bxsys.taskr.model.service.api.ISnackbarService
import com.bxsys.taskr.model.service.api.IUserService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

interface ISignInViewModel {
    var email: String
    var password: String

    fun onEmailChange(newVal: String)
    fun onPasswordChange(newVal: String)
    fun onSignInClick(navFromTo: (String, String) -> Unit)
    fun onSignUpClick(nav: (String) -> Unit)
}

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val userService: IUserService,
    logService: ILogService,
    snackbarService: ISnackbarService
): TaskrViewModel(logService, snackbarService), ISignInViewModel {

    override var email by mutableStateOf("")
    override var password by mutableStateOf("")

    override fun onEmailChange(newVal: String) {
        email = newVal
    }

    override fun onPasswordChange(newVal: String) {
        password = newVal
    }

    override fun onSignInClick(navFromTo: (String, String) -> Unit) {
        userService.signIn(email, password) { error ->
            if (error == null) {
                navFromTo(SIGN_IN_SCREEN, HOME_SCREEN)
            } else {
                onError(error)
            }

        }
    }

    override fun onSignUpClick(nav: (String) -> Unit) {
        nav(SIGN_UP_SCREEN)
    }
}

class MockSignInViewModel: ISignInViewModel {
    override var email: String = ""
    override var password: String = ""

    override fun onEmailChange(newVal: String) {}
    override fun onPasswordChange(newVal: String) {}
    override fun onSignInClick(navFromTo: (String, String) -> Unit) {}
    override fun onSignUpClick(nav: (String) -> Unit) {}
}