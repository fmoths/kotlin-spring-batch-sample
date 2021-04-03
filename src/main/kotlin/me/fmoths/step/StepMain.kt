package me.fmoths.step

import me.fmoths.entity.Book
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.item.ItemProcessor
import org.springframework.batch.item.ItemReader
import org.springframework.batch.item.ItemWriter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component

@Configuration("${StepMain.STEP_NAME}-config")
class StepMain @Autowired constructor(
    private val stepBuilderFactory: StepBuilderFactory,
    private val reader : StepMainReader,
    private val writer : StepMainWriter,
    private val processor: StepMainProcessor,
    @Value("\${batch.step.page-size}")
    private val pageSize: Int
){
    companion object {
        const val STEP_NAME: String = "stepMain"
    }

    @Bean(STEP_NAME)
    fun step() = stepBuilderFactory.get(STEP_NAME)
        .chunk<Book, String>(pageSize)
        .reader(reader)
        .processor(processor)
        .writer(writer)
        .build()
}

@StepScope
@Component
class StepMainReader : ItemReader<Book>{
    override fun read(): Book? {
        TODO("Not yet implemented")
    }
}

@StepScope
@Component
class StepMainProcessor : ItemProcessor<Book, String> {
    override fun process(item: Book): String? =
        item.toString()
}

@StepScope
@Component
class StepMainWriter : ItemWriter<String> {
    override fun write(items: MutableList<out String>) {
        TODO("Not yet implemented")
    }
}