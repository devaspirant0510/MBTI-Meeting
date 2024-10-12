package org.ngod.mbtisns.data.repository

import org.ngod.mbtisns.data.entity.Member
import org.ngod.mbtisns.data.entity.ProfileImage
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface MemberRepository :JpaRepository<Member,Long>{
    @Modifying
    @Transactional
    @Query("update Member m set m.profileImage = :image where m.id=:id")
    fun updateByProfileImage(id:Long, image: ProfileImage)
    @Query("SELECT * FROM member ORDER BY RANDOM() LIMIT 4", nativeQuery = true)
    fun findRandomAccount(): List<Member>
}