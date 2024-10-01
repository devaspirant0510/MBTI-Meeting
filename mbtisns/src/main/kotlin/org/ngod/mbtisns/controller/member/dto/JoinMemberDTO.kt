package org.ngod.mbtisns.controller.member.dto

import org.ngod.mbtisns.data.entity.Member

data class JoinMemberDTO(
    var accountId:Long,
    var member: Member
)