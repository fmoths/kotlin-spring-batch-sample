package me.fmoths

import me.fmoths.constants.BatchArgumentKey
import me.fmoths.constants.JobNames
import me.fmoths.exception.UndefinedJobConfigException
import me.fmoths.job.JobConfigMain
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@EnableBatchProcessing
@SpringBootApplication
class KotlinSpringBatch
fun main(args: Array<String>){
    runApplication<KotlinSpringBatch>(*args).use {
        val jobConfig = when(it.environment.getProperty(BatchArgumentKey.JOBNAME) ?: JobNames.MAIN) {
            JobNames.DEFAULT,
            JobNames.MAIN -> it.getBean(JobConfigMain::class.java)
            else -> throw UndefinedJobConfigException("[KotlinSpringBatch.main] 존재하지 않는 JobName입니다.")
        }

        jobConfig.runJob()
    }
}