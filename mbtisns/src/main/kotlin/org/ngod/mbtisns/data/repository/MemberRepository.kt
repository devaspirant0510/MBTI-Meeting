package org.ngod.mbtisns.data.repository

import org.ngod.mbtisns.data.entity.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberRepository :JpaRepository<Member,Long>{}