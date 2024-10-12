package org.ngod.mbtisns.data.entity

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.*

@Entity
@Schema
data class ProfileImage(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?=null,
    @OneToOne
    @JoinColumn(name = "image_file_id", unique = true)
    var file: ImageFile
)
