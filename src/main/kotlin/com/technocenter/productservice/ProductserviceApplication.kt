package com.technocenter.productservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class ProductserviceApplication

fun main(args: Array<String>) {
	runApplication<ProductserviceApplication>(*args)
}
