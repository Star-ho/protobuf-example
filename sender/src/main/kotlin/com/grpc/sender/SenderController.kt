package com.grpc.sender

import com.grpc.receiver.OrderGrpcRepositoryGrpc.OrderGrpcRepositoryBlockingStub
import com.grpc.receiver.OrderGrpcRepositoryGrpcKt
import com.grpc.receiver.OrderRequest
import com.grpc.receiver.OrderResponse
import io.grpc.Channel
import io.grpc.ManagedChannelBuilder
import io.grpc.stub.StreamObserver
import kotlinx.coroutines.runBlocking
import net.devh.boot.grpc.client.inject.GrpcClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController


@RestController
class SenderController(
    private val protobufHttpMessageConverter: ProtobufHttpMessageConverter,
    private val orderRepository: OrderRepository,
) {

    private val channel: Channel =
        ManagedChannelBuilder
            .forAddress("localhost", 6565)
            .usePlaintext()
            .build()


    @GetMapping("/order/{id}")
    fun getOrder(@PathVariable id:Long): OrderResponse {
        return runBlocking{ orderRepository.aaa(id) }

    }
}

@Service
class OrderRepository(
    @GrpcClient("local-grpc-server")
    private val stub:OrderGrpcRepositoryGrpcKt.OrderGrpcRepositoryCoroutineStub
//    @GrpcClient("local-grpc-server")
//    private val stub:OrderGrpcRepositoryBlockingStub
){
    suspend fun aaa(id:Long): OrderResponse {
        val req = OrderRequest.newBuilder()
            .setId(id.toInt())
            .build()
        return stub.findOrder(req)
    }
}

@Configuration
class ProtobufConf{
    @Bean
    fun protobufHttpMessageConverter(): ProtobufHttpMessageConverter? {
        return ProtobufHttpMessageConverter()
    }
}
