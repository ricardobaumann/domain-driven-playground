package com.github.ricardobaumann.reportservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ReportServiceApplication

fun main(args: Array<String>) {
	runApplication<ReportServiceApplication>(*args)
}
