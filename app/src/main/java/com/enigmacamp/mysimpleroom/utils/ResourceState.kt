package com.enigmacamp.mysimpleroom.utils

sealed class ResourceState<out T> {
    object Idle : ResourceState<Nothing>()
    object Loading : ResourceState<Nothing>()
    data class Success<T>(val data: T) : ResourceState<T>()
    data class Failed(val message: String?) : ResourceState<Nothing>()
}
