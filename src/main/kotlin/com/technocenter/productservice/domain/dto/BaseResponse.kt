package com.technocenter.productservice.domain.dto

//STANDARD RESPONSE
data class BaseResponse<T>( //T merupakan tipe data generik (fleksibel)
    val message: String,
    val status: Int = 200,
    val success: Boolean = true,
    val data: T? = null
)
