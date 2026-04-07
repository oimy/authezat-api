package soia.authezat

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AuthezatApplication

fun main(args: Array<String>) {
	runApplication<AuthezatApplication>(*args)
}
