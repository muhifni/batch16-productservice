package com.technocenter.productservice.domain.entity

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.sql.Timestamp

@MappedSuperclass
abstract class BaseEntity(
    @CreationTimestamp
    @Column(nullable = false)
    val createdAt: Timestamp? = null,

    @Column
    var createdBy: String? = null,

    @UpdateTimestamp
    @Column(nullable = false)
    val updatedAt: Timestamp? = null,

    @Column(nullable = false)
    var isDelete: Boolean = false,
)
