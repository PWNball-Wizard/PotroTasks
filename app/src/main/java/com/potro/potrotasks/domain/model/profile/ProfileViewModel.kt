package com.potro.potrotasks.domain.model.profile

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class ProfileViewModel : ViewModel() {
    private val _state = MutableStateFlow(ProfileUIState())
    val state = _state.asStateFlow()

    init {
        getUserInfo()
    }

    fun getUserInfo() =
        _state.update { it.copy(profilePictureLink = "https://i.pinimg.com/564x/20/0f/50/200f509408e5ae122d1a45d110f2faa2.jpg") }

    fun onChangeFirstName(newValue: String) = _state.update { it.copy(firstName = newValue) }

    fun onChangeLastName(newValue: String) = _state.update { it.copy(lastName = newValue) }

    fun onChangeLocation(newValue: String) = _state.update { it.copy(location = newValue) }

    fun onChangeEmail(newValue: String) = _state.update { it.copy(email = newValue) }

    fun onChangePhone(newValue: String) = _state.update { it.copy(phone = newValue) }

    fun onSaveUserInfo() {
        TODO()
    }
}