package com.example.mipt_example

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

sealed class SignInEvent {
    class FillLogin(val login: String): SignInEvent()
    class FillEmail(val email: String): SignInEvent()
    class FillPassword(val password: String): SignInEvent()
    object ShowPassword: SignInEvent()
    object CheckKeepSignIn: SignInEvent()
    object CheckEmailMe: SignInEvent()

}

data class SignInState (
    val login: String = "",
    val email: String = "",
    val password: String = "",
    val hidePass: Boolean = true,
    val keepSignInCheck: Boolean = true,
    val emailMe: Boolean = true
)

class SignInViewModel: ViewModel() {
    private val _viewState = MutableStateFlow(SignInState())
    val viewState: StateFlow<SignInState> = _viewState
    fun obtainEvent(event: SignInEvent) {
        when (event) {
            is SignInEvent.FillLogin -> inputLogin(event.login)
            is SignInEvent.FillEmail -> inputEmail(event.email)
            is SignInEvent.FillPassword -> inputPassword(event.password)
            SignInEvent.ShowPassword -> showHidePassword()
            SignInEvent.CheckEmailMe -> checkEmailMe()
            SignInEvent.CheckKeepSignIn -> checkKeepSignIn()
        }
    }

    private fun inputLogin(value: String) {
        _viewState.value = _viewState.value.copy(login = value)
    }

    private fun inputEmail(value: String) {
        _viewState.value = _viewState.value.copy(email = value)
    }

    private fun inputPassword(value: String) {
        _viewState.value = _viewState.value.copy(password = value)
    }

    private fun showHidePassword() {
        _viewState.value = _viewState.value.copy(hidePass = !_viewState.value.hidePass)
    }

    private fun checkKeepSignIn() {
        _viewState.value = viewState.value.copy(keepSignInCheck = !_viewState.value.keepSignInCheck)
    }

    private fun checkEmailMe() {
        _viewState.value = viewState.value.copy(emailMe = !_viewState.value.emailMe)
    }

}