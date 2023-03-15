package com.grpc.receiver

import com.grpc.receiver.OrderGrpcRepositoryGrpc.OrderGrpcRepositoryImplBase
import io.grpc.stub.StreamObserver
import org.lognet.springboot.grpc.GRpcService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter
import kotlin.coroutines.EmptyCoroutineContext

@Configuration
class ProtobufConf{
    @Bean
    fun protobufHttpMessageConverter(): ProtobufHttpMessageConverter? {
        return ProtobufHttpMessageConverter()
    }
}

@GRpcService
class Tesdfs: OrderGrpcRepositoryGrpcKt.OrderGrpcRepositoryCoroutineImplBase(

){
    override suspend fun findOrder(request: OrderRequest): OrderResponse {
        return if(request?.id == 1) firstMessage()
        else secondMessage()
    }
}
//@GRpcService
//class Tesdfs: OrderGrpcRepositoryImplBase(
//
//){
//    override fun findOrder(request: OrderRequest?, responseObserver: StreamObserver<OrderResponse>?) {
//        val a = if(request?.id == 1) firstMessage()
//        else secondMessage()
//        responseObserver?.onNext(a)
//        responseObserver?.onCompleted()
//    }
//}

fun firstMessage(): OrderResponse {
    println(2222)
    val member = MemberResponse.newBuilder()
        .setId(1)
        .setEmail("email1")
        .addPhone(0, MemberResponse.PhoneNumber.newBuilder().setNumber("111").setType(MemberResponse.PhoneNumber.PhoneType.MOBILE).build())
        .setName("name1")
        .build()
    val menu = MenuResponse.newBuilder()
        .setId(1)
        .setName("menu1")
        .setPrice(123123)
        .build()
    return OrderResponse.newBuilder()
        .setId(1)
        .addMember(0,member)
        .addMenu(0,menu)
        .build()
}
fun secondMessage(): OrderResponse {
    val member = MemberResponse.newBuilder()
        .setId(2)
        .setEmail("email2")
        .addPhone(0, MemberResponse.PhoneNumber.newBuilder().setNumber("111").setType(MemberResponse.PhoneNumber.PhoneType.MOBILE).build())
        .setName("name2")
        .build()
    val menu = MenuResponse.newBuilder()
        .setId(2)
        .setName("menu2")
        .setPrice(123123)
        .build()
    return OrderResponse.newBuilder()
        .setId(2)
        .addMember(0,member)
        .addMenu(0,menu)
        .build()
}