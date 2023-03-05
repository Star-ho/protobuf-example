package com.grpc.sender

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter

@SpringBootApplication
class SenderApplication

fun main(args: Array<String>) {
    runApplication<SenderApplication>(*args)
}
