package org.ngod.mbtisns.controller.member

import lombok.extern.slf4j.Slf4j
import org.ngod.mbtisns.controller.member.dto.JoinMemberDTO
import org.ngod.mbtisns.core.ApiException
import org.ngod.mbtisns.core.ApiResponse
import org.ngod.mbtisns.data.entity.Account
import org.ngod.mbtisns.data.entity.Member
import org.ngod.mbtisns.data.entity.ProfileImage
import org.ngod.mbtisns.data.entity.enum.FileType
import org.ngod.mbtisns.domain.service.AuthService
import org.ngod.mbtisns.domain.service.FileService
import org.ngod.mbtisns.domain.service.JwtService
import org.ngod.mbtisns.domain.service.MemberService
import org.springframework.core.io.ClassPathResource
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption

@Slf4j
@RestController
@RequestMapping("/api/v1/member")
class MemberController(
    val service: MemberService,
    val authService: AuthService,
    val fileService: FileService,
    val jwtService: JwtService
) : MemberSwagger {
    @PatchMapping("/follow/{myId}/{followId}")
    override fun followUser(
        @RequestHeader("Authorization")
        authorization: String,
        @PathVariable(name = "myId")
        myId: Long,
        @PathVariable(name = "followId")
        followId: Long
    ): ApiResponse<Account> {
        println("patch follow $myId $followId")

        val jwt = jwtService.verifyToken(authorization)
        service.followUser(myId, followId)
        return ApiResponse.success(authService.readLoginUser(jwt?.sub!!))


    }

    @DeleteMapping("/unfollow/{myId}/{followId}")
    override fun unFollowUser(
        @RequestHeader("Authorization")
        authorization: String,
        @PathVariable(name = "myId")
        myId: Long,
        @PathVariable(name = "followId")
        followId: Long
    ): ApiResponse<Account> {
        val jwt = jwtService.verifyToken(authorization)
        service.unFollowUser(myId,followId)
        return ApiResponse.success(authService.readLoginUser(jwt?.sub!!))
    }

    @GetMapping("/{memberId}")
    override fun getMember(
        @RequestHeader("Authorization") authorization: String,
        @PathVariable(name = "memberId") memberId: Long
    ): ApiResponse<Member> {
        jwtService.verifyToken(authorization)
        return ApiResponse.success(service.getMemberById(memberId))

    }


    @PostMapping
    override fun joinMember(
        @RequestHeader("Authorization") token: String,
        @RequestBody member: JoinMemberDTO
    ): Member {
        service.checkMemberAuthorization(token)
        return service.memberSave(member)
    }

    @PostMapping("/upload")
    override fun uploadImage(
        @RequestHeader("Authorization")
        authorization: String,
        @RequestParam("file")
        file: MultipartFile
    ): ApiResponse<Member> {
        val data = jwtService.verifyToken(authorization)
        val account = authService.readLoginUser(data?.sub!!)
        val profileImage = fileService.saveFile<ProfileImage>(file, FileType.PROFILE)
        if (account.member == null) {
            throw ApiException(HttpStatus.UNAUTHORIZED.value(), "가입후 이용 가능한 서비스 입니다.")
        }
        val member = service.updateMemberProfileImage(account.member?.id!!, profileImage)
        return ApiResponse.success(member)
    }

    @GetMapping("/recommend")
    override fun getRecommendUser(): ApiResponse<List<Member>> {
        return ApiResponse.success(service.getRecommendUser())
    }

}