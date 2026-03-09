package com.technocenter.productservice.domain.constant

object ConstantVariable {
    val HEADER_USER_ID = "X-USER-ID"
    val HEADER_AUTHORITY = "Authority"

    public object KafkaTopic {
        const val DELETE_USER_PRODUCT = "BATCH16_DELETE_USER_PRODUCT"
    }
}