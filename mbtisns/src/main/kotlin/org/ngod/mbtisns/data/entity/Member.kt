package org.ngod.mbtisns.data.entity

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.persistence.*
import java.util.*

@Schema(name = "Account", description = "사용자")
@Entity
data class Member(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,

    @Schema(name = "nickName", description = "유저 닉네임")
    @Column(name = "nick_name", nullable = true, length = 20)
    var nickName: String?,


    @Enumerated(EnumType.STRING)
    var mbti: Mbti? = null,  // Enum type for MBTI

    @Column(name = "created_at")
    var createdAt: Date? = null,

    @OneToOne
    @JoinColumn(name = "account_id", nullable = false)  // 외래키 설정 (account의 id를 참조)
    var account: Account? = null,

    @OneToOne(mappedBy = "member", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var profileImage: ProfileImage? = null
)