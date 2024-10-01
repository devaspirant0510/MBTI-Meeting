package org.ngod.mbtisns.data.entity

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.*
import java.util.*

@Schema(name = "ProfileImage", description = "유저 프로필 이미지")
@Entity(name = "profile_image")
data class ProfileImage(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,

    @Schema(name = "profileURL", description = "프로필 사진 URL")
    @Column(name = "profile_url", nullable = true, length = 255)
    var nickName: String?,

    @Column(name = "created_at")
    var createdAt: Date? = null,

    @OneToOne
    @JoinColumn(name = "member_id", nullable = false)  // 외래키 설정 (account의 id를 참조)
    var member: Member? = null
)