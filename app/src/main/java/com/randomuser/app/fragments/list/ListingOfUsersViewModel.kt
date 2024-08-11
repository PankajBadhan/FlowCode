package com.randomuser.app.fragments.list

import android.app.Application
import androidx.compose.runtime.*
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.randomuser.app.data.User
import com.randomuser.app.repositories.UsersRepository
import com.randomuser.app.utils.isInternetAvailable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class ListingOfUsersViewModel @Inject constructor(application: Application, private val usersRepository: UsersRepository)
    : AndroidViewModel(application) {

    private val context
        get() = getApplication<Application>()

    private val showNoInternet: MutableLiveData<Boolean> = MutableLiveData(false)
    var loading: MutableState<Boolean> = mutableStateOf(false)

    private val mutableUsersList = mutableStateListOf<User>()
    val usersList: List<User>
        get() = mutableUsersList

    private var job: Job? = null
    var errorMessage: String by mutableStateOf("")
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onApiError("Exception: ${throwable.localizedMessage}")
    }

    init {
        getUsers()
    }

    private fun getUsers() {
        if(isInternetAvailable(context)) {
            loading.value = true
            job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
                val response = usersRepository.getAllUsers()
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            mutableUsersList.clear()
                            mutableUsersList.addAll(it.results)
                        }
                        loading.value = false
                    } else {
                        onApiError("Error : ${response.message()} ")
                    }
                }
            }
        } else {
            showNoInternet.value = true;
        }
    }

    private fun onApiError(message: String) {
        errorMessage = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

    fun getShowNoInternet(): LiveData<Boolean> {
        return showNoInternet
    }

    fun setShowNoInternet(showNoInternet: Boolean) {
        this.showNoInternet.value = showNoInternet
    }
}