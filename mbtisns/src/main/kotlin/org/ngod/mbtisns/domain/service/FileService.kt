package org.ngod.mbtisns.domain.service

import org.ngod.mbtisns.data.entity.Article
import org.ngod.mbtisns.data.entity.ArticleImage
import org.ngod.mbtisns.data.entity.ImageFile
import org.ngod.mbtisns.data.entity.ProfileImage
import org.ngod.mbtisns.data.entity.enum.FileType
import org.ngod.mbtisns.data.repository.ArticleImageRepository
import org.ngod.mbtisns.data.repository.ImageFileRepository
import org.ngod.mbtisns.data.repository.ProfileImageRepository
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption

@Service
class FileService(
    private val repository: ImageFileRepository,
    private val profileImageRepository: ProfileImageRepository,
    private val articleImageRepository: ArticleImageRepository
) {
    companion object {
        const val UPLOAD_PATH = "/Users/kotlinandnode/seungho/uploads"
    }
    fun saveFileArticle(file:MultipartFile,article:Article):ArticleImage{
        val uploadDir = UPLOAD_PATH
        val path = Paths.get(uploadDir + File.separator + file.originalFilename)
        println("$uploadDir $path")
        Files.copy(file.inputStream, path, StandardCopyOption.REPLACE_EXISTING)
        val imageFile = ImageFile(profileUrl = path.toString())
        val savedImage = repository.save(imageFile)
        val articleImage = ArticleImage(file = savedImage, article = article)
        return articleImageRepository.save(articleImage)
    }

    fun <T > saveFile(file: MultipartFile, type: FileType): T {
        val uploadDir = UPLOAD_PATH
        val path = Paths.get(uploadDir + File.separator + file.originalFilename)
        println("$uploadDir $path")
        Files.copy(file.inputStream, path, StandardCopyOption.REPLACE_EXISTING)
        val imageFile = ImageFile(profileUrl = path.toString())
        val savedImage = repository.save(imageFile)
        if (type == FileType.PROFILE) {
            val profileImage = ProfileImage(file = savedImage)
            return profileImageRepository.save(profileImage) as T
        } else if (type == FileType.ARTICLE) {
            val articleImage = ArticleImage(file = savedImage,)
            return articleImageRepository.save(articleImage) as T

        } else {
            return ProfileImage(file = savedImage) as T
        }
    }
}