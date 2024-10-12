package org.ngod.mbtisns.data.repository

import org.ngod.mbtisns.data.entity.ProfileImage
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProfileImageRepository:JpaRepository<ProfileImage,Long> {
}