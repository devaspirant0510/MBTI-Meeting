package org.ngod.mbtisns

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MbtiSnsApplication{
	@Value("\${DB_PASSWORD:defaultPassword}") // .env 파일에서 직접 참조
	lateinit var dbPassword: String
	fun printPassword() {
		println("DB Password: $dbPassword")
	}

}

fun main(args: Array<String>) {
	val context = runApplication<MbtiSnsApplication>(*args)
	val app = context.getBean(MbtiSnsApplication::class.java)
	app.printPassword()
}
