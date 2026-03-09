package com.technocenter.productservice.consumer

import com.technocenter.productservice.domain.constant.ConstantVariable
import com.technocenter.productservice.service.ProductService
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.KafkaHandler
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.annotation.KafkaListeners

@Configuration
class UserManagementConsumer(
    private val productService: ProductService
) {
    val log = LoggerFactory.getLogger(this::class.java)
    @KafkaListeners(
        value = [
            KafkaListener(
                autoStartup = "true",
                containerFactory = "deleteUserProductHandlerContainerFactory",
                id = "BATCH16_DELETE_USER_PRODUCT",
                topics = ["BATCH16_DELETE_USER_PRODUCT"],
            )
        ]
//        groupId = ["BATCH16_DELETE_USER_PRODUCT"]
    )
    @KafkaHandler
    fun deleteUserProductHandler(message: Int){
        log.info("Received message: $message of topic BATCH16_DELETE_USER_PRODUCT")
        productService.deleteUserProduct(message)
    }

}