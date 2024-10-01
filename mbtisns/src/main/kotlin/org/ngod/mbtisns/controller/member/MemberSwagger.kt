package org.ngod.mbtisns.controller.member

import org.ngod.mbtisns.controller.member.dto.JoinMemberDTO
import org.ngod.mbtisns.data.entity.Member

interface MemberSwagger {
    fun joinMember(  authorization:String,member: JoinMemberDTO):Member
}