package com.technocenter.productservice.exception

import org.springframework.http.HttpStatus

class GeneralException(
    val httpStatus: HttpStatus,
    override val message: String
): RuntimeException(message){
}