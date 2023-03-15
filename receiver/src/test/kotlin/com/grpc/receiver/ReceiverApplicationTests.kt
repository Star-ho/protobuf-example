package com.grpc.receiver

import io.grpc.Channel
import io.grpc.ManagedChannelBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestConstructor
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@ActiveProfiles("test")
annotation class TestBase
@SpringBootTest(
        classes = [ReceiverApplication::class]
)
@TestBase
class ReceiverApplicationTests {

    @Test
    fun contextLoad111s() {
        println("0000")
        val channel: Channel =
                ManagedChannelBuilder
                        .forAddress("localhost", 8081)
                        .usePlaintext()
                        .build()

        println("1111")
        val stub = OrderGrpcRepositoryGrpcKt.OrderGrpcRepositoryCoroutineStub(channel)
        val req = OrderRequest.newBuilder()
                .setId(1L.toInt())
                .build()
        println("2222")
        val a = CoroutineScope(Dispatchers.IO).async {
            stub.findOrder(req)
            println("33333")
        }
        println("4444")
        runBlocking {
            a.await()
        }

    }
}
