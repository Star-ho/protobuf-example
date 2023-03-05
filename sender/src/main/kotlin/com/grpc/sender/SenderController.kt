package com.grpc.sender

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpOutputMessage
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpRequest.BodyPublishers
import java.net.http.HttpResponse
import java.net.http.HttpResponse.BodyHandlers

@RestController
class SenderController(
    private val protobufHttpMessageConverter: ProtobufHttpMessageConverter
) {
    @GetMapping("/order/{id}")
    fun getOrder(@PathVariable id:Long): HttpResponse<ByteArray>? {
        val protobufRequest = OrderGrpc.OrderRequest.newBuilder()
            .setId(id.toInt())
            .build()

        val httpRequest = HttpRequest.newBuilder()
            .POST(BodyPublishers.ofByteArray(protobufRequest.toByteArray()))
            .uri(URI("http://localhost:8081"))
            .setHeader("Content-Type", "application/protobuf")
            .build()
        println("11111")
        val res = HttpClient
            .newHttpClient()
            .send(httpRequest,BodyHandlers.ofByteArray())
        println("222222")
        println(res.statusCode())
        println(res.body())
        println(res)
        val res2 = OrderGrpc.OrderRequest.parseFrom(res.body())
        return res
    }

    @GetMapping("/order2/{id}")
    fun getOrder2(@PathVariable id:Long): OrderGrpc.Testaaa? {

        val httpRequest = HttpRequest.newBuilder()
            .uri(URI("http://localhost:8081/${id}"))
            .setHeader("Content-Type", "application/protobuf")
            .build()
        println("11111")
        val res = HttpClient
            .newHttpClient()
            .send(httpRequest,BodyHandlers.ofByteArray())
        println("222222")
        println(res.statusCode())
        println(res.body())
        println(res)
//        val res2 = OrderGrpc.OrderRequest.parseFrom(res.body())

        return OrderGrpc.Testaaa.parseFrom(res.body())
    }
    private fun getClient() = HttpClient
        .newHttpClient()
}

@Configuration
class ProtobufConf{
    @Bean
    fun protobufHttpMessageConverter(): ProtobufHttpMessageConverter? {
        return ProtobufHttpMessageConverter()
    }
}
