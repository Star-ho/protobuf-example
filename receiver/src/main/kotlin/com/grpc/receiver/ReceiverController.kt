package com.grpc.receiver

import com.grpc.receiver.OrderGrpc.MemberResponse.PhoneNumber
import com.grpc.receiver.OrderGrpc.Testaaa
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ReceiverController {
    @PostMapping(
//        consumes = ["application/x-protobuf"],
        produces = ["application/x-protobuf"])
    fun gerOrder(@RequestBody orderRequest: OrderGrpc.OrderRequest): OrderGrpc.OrderResponse {
        println("11111111")
        if(orderRequest.id == 1){
            return firstMessage()
        }else{
            return secondMessage()
        }
    }

    @GetMapping(
//        consumes = ["application/x-protobuf"],
        produces = ["application/x-protobuf"],
        path = ["/{id}"]
    )
    fun gerOrder2(@PathVariable id:Int): Testaaa? {
        println("11111111")
        val a = Testaaa.newBuilder()
        a.id = 1
        a.name = "123123"
        return a.build()
    }
    fun firstMessage(): OrderGrpc.OrderResponse {
        val member = OrderGrpc.MemberResponse.newBuilder()
            .setId(1)
            .setEmail("email1")
            .setPhone(1,PhoneNumber.newBuilder().setNumber("111").setType(PhoneNumber.PhoneType.MOBILE).build())
            .setName("name1")
            .build()
        val menu = OrderGrpc.MenuResponse.newBuilder()
            .setId(1)
            .setName("menu1")
            .setPrice(123123)
            .build()
        return OrderGrpc.OrderResponse.newBuilder()
            .setId(1)
            .setMember(1,member)
            .setMenu(1,menu)
            .build()
    }
    fun secondMessage(): OrderGrpc.OrderResponse {
        val member = OrderGrpc.MemberResponse.newBuilder()
            .setId(2)
            .setEmail("email2")
            .setPhone(1,PhoneNumber.newBuilder().setNumber("111").setType(PhoneNumber.PhoneType.MOBILE).build())
            .setName("name2")
            .build()
        val menu = OrderGrpc.MenuResponse.newBuilder()
            .setId(2)
            .setName("menu2")
            .setPrice(123123)
            .build()
        return OrderGrpc.OrderResponse.newBuilder()
            .setId(2)
            .setMember(0,member)
            .setMenu(0,menu)
            .build()
    }
}

@Configuration
class ProtobufConf{
    @Bean
    fun protobufHttpMessageConverter(): ProtobufHttpMessageConverter? {
        return ProtobufHttpMessageConverter()
    }
}
