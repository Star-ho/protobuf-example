package com.grpc.sender


import io.grpc.Channel
import io.grpc.ManagedChannelBuilder
import kotlinx.coroutines.runBlocking
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController


@RestController
class SenderController(
    private val protobufHttpMessageConverter: ProtobufHttpMessageConverter,
) {

    private val channel: Channel =
        ManagedChannelBuilder
            .forAddress("localhost", 6565)
            .usePlaintext()
            .build()


//    private val channel =
//        NettyChannelBuilder
//            .forAddress("localhost",8080)
//            .build()

    @GetMapping("/order/{id}")
    fun getOrder(@PathVariable id:Long): OrderResponse {
//        val stub = OrderGrpcRepositoryGrpc.newBlockingStub(channel)
        val stub = OrderGrpcRepositoryGrpcKt.OrderGrpcRepositoryCoroutineStub(channel)
        val req = OrderRequest.newBuilder()
            .setId(id.toInt())
            .build()
        println("1111")
        val a = runBlocking{ stub.findOrder(req) }
        println("3333")
        return a
    }
}


@Configuration
class ProtobufConf{
    @Bean
    fun protobufHttpMessageConverter(): ProtobufHttpMessageConverter? {
        return ProtobufHttpMessageConverter()
    }
}
