package com.technocenter.productservice.domain.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.sql.Timestamp

@Entity
@Table(name = "mst_product")
data class MasterProductEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Auto Increment
    var id: Int? = null,

    var name: String,

    var stock: Int = 0,

    var price: Long,
): BaseEntity()
