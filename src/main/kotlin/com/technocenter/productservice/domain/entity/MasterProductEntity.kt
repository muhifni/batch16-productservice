package com.technocenter.productservice.domain.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.sql.Timestamp

@Entity
@Table(name = "mst_product")
data class MasterProductEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column(nullable = false)
    var name: String,

    @Column(nullable = false)
    var stock: Int = 0,

    @Column(nullable = false)
    var price: Long,

    // Relasi Many to One ke Category
    // Banyak product bisa punya 1 category yang sama
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = true)
    var category: MasterCategoryEntity? = null

) : BaseEntity()
