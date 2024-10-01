package org.ngod.mbtisns.controller.member

import lombok.extern.slf4j.Slf4j
import org.ngod.mbtisns.controller.member.dto.JoinMemberDTO
import org.ngod.mbtisns.data.entity.Member
import org.ngod.mbtisns.service.MemberService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Slf4j
@RestController
@RequestMapping("/api/v1/member")
class MemberController(val service:MemberService) :MemberSwagger{

    @PostMapping
    override fun joinMember(
        @RequestHeader("Authorization") token:String,
        @RequestBody member: JoinMemberDTO
    ): Member {
        print(token)

        service.checkMemberAuthorization(token)
        return service.memberSave(member)
    }
}