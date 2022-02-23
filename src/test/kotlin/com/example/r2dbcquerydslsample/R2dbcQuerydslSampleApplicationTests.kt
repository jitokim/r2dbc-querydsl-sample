package com.example.r2dbcquerydslsample

import kotlinx.coroutines.reactive.awaitSingle
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class R2dbcQuerydslSampleApplicationTests {


    @Autowired
    private lateinit var sampleRepository: SampleRepository

    @Autowired
    private lateinit var childRepository: ChildRepository

    @Test
    fun `test`() {
        runBlocking {

            val list = listOf(
                    Sample(title = "1"),
                    Sample(title = "2"),
                    Sample(title = "3"),
                    Sample(title = "4")
            )

            // save test
            sampleRepository.saveAll(list).collectList().awaitSingle()
            var count = sampleRepository.count().awaitSingle()
            Assertions.assertEquals(list.size.toLong(), count)


            // sample entities load
            val samples = sampleRepository.query { query ->
                query.select(sampleRepository.entityProjection())
                        .from(QSample.sample)
            }.all().collectList().awaitSingle()

            samples.forEach {
                println("id:${it.id}, title:${it.title}")
            }

            count = sampleRepository.count().awaitSingle()

            Assertions.assertEquals(samples.size.toLong(), count)

            // child save
            val children = listOf(
                    Child(sampleId = 1L, title = "1"),
                    Child(sampleId = 1L, title = "1"),
                    Child(sampleId = 1L, title = "1"),
                    Child(sampleId = 1L, title = "1")
            )
            childRepository.saveAll(children).collectList().awaitSingle()

            val childrenCount = childRepository.count().awaitSingle()

            Assertions.assertEquals(children.size.toLong(), childrenCount)


            // select join query
            val samplesHaveChildren = sampleRepository.query { query ->
                query.select(sampleRepository.entityProjection())
                        .distinct()
                        .from( QSample.sample )
                        .innerJoin( QChild.child )
                        .on( QSample.sample.id.eq(QChild.child.sampleId) )
            }.all().collectList().awaitSingle()

            samplesHaveChildren.forEach {
                println("id:${it.id}, title:${it.title}")
            }

            Assertions.assertEquals(1, samplesHaveChildren.count() )

        }

    }

}
