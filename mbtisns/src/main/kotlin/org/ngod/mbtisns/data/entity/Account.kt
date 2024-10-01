package org.ngod.mbtisns.data.entity

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.*
import lombok.Getter
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

@Schema(name = "Account", description = "계정")
@Entity
data class Account(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,

    @Schema(name = "provider", description = "프로바이더", example = "kakao,google,facebook")
    @Column(nullable = false, length = 30)
    var provider: String,

    @Schema(name = "uid", description = "유저 고유 UID")
    @Column(nullable = false, length = 200)
    var uid: String,

    @Schema(name = "email", description = "이메일")
    @Column(nullable = true, length = 40)
    var email: String? = null,

    @CreationTimestamp
    @Schema(name = "createdAt", description = "SNS 계정 연동일")
    @Column(name = "created_at")
    var createdAt: LocalDateTime? = null,

    @OneToOne(mappedBy = "account", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var member: Member? = null,
)