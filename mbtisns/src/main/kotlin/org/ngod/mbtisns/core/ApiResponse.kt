package org.ngod.mbtisns.core

data class ApiResponse<T>(
    var success: Boolean,
    var message: String,
    var data: T?=null,
    var error: ApiError? = null
) {
    companion object {
        fun <T> success(data: T, message: String = "success"): ApiResponse<T> {
            return ApiResponse(success = true, message = message, data = data,error=null)
        }
        fun <T> error(error:ApiError,message:String="An unexpected error occurred."):ApiResponse<T>{
            return ApiResponse(success = false, message = message, error =error)
        }
    }
}