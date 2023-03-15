package com.grpc.receiver


import com.grpc.receiver.OrderGrpcRepositoryGrpc.OrderGrpcRepositoryBlockingStub
import com.grpc.receiver.OrderGrpcRepositoryGrpc.OrderGrpcRepositoryImplBase
import io.grpc.stub.StreamObserver
import net.devh.boot.grpc.server.service.GrpcService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter

@Configuration
class ProtobufConf{
    @Bean
    fun protobufHttpMessageConverter(): ProtobufHttpMessageConverter? {
        return ProtobufHttpMessageConverter()
    }
}

@GrpcService
class AAA: OrderGrpcRepositoryImplBase() {
    override fun findOrder(request: OrderRequest?, responseObserver: StreamObserver<OrderResponse>?) {
        val res = if(request?.id == 1)firstMessage()
        else secondMessage()
        responseObserver?.onNext(res)
        responseObserver?.onCompleted()
    }

    fun firstMessage(): OrderResponse {
        val member = MemberResponse.newBuilder()
            .setId(1)
            .setEmail("email1")
            .addPhone(MemberResponse.PhoneNumber.newBuilder().setNumber("111").setType(MemberResponse.PhoneNumber.PhoneType.MOBILE).build())
            .setName("name1")
            .build()
        val menu = MenuResponse.newBuilder()
            .setId(1)
            .setName("menu1")
            .setPrice(123123)
            .build()
        return OrderResponse.newBuilder()
            .setId(1)
            .addMember(member)
            .addMenu(menu)
            .build()
    }
    fun secondMessage(): OrderResponse {
        val member = MemberResponse.newBuilder()
            .setId(2)
            .setEmail("email2")
            .addPhone(MemberResponse.PhoneNumber.newBuilder().setNumber("111").setType(MemberResponse.PhoneNumber.PhoneType.MOBILE).build())
            .setName("name2")
            .build()
        val menu = MenuResponse.newBuilder()
            .setId(2)
            .setName("menu2")
            .setPrice(123123)
            .build()
        return OrderResponse.newBuilder()
            .setId(2)
            .addMember(member)
            .addMenu(menu)
            .build()
    }
}