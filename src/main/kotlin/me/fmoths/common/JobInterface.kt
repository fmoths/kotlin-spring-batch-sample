package me.fmoths.common

import me.fmoths.constants.BatchArgument
import me.fmoths.constants.JobParameterKey
import org.springframework.batch.core.ExitStatus
import org.springframework.batch.core.JobExecution
import org.springframework.batch.core.JobParametersBuilder
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.launch.JobLauncher
import java.time.ZoneId
import java.util.*

abstract class JobInterface(
    jobLauncher: JobLauncher,
    jobBuilderFactory: JobBuilderFactory,
    private val batchArgument: BatchArgument,
    val jobName: String
) : JobLauncher by jobLauncher {
    abstract fun getJobFlow(): SimpleFlowHolder

    val flow by lazy {
        jobBuilderFactory
            .get("${jobName}Job")
            .start(getJobFlow().build())
            .end()
            .build()
    }

    open fun runJob(): JobExecution {
        var ret: JobExecution
        var criterialDate = batchArgument.criterialDate

        LOG.info("#####################################################")
        LOG.info(" Job is executing with")
        LOG.info(" criterialDate = $criterialDate")
        LOG.info("#####################################################")

        ret = run(
            flow,
            JobParametersBuilder()
                .addDate(JobParameterKey.CRITERIAL_DATE, Date.from(criterialDate.atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .toJobParameters()
        )

        if (ExitStatus.COMPLETED.equals(ret.exitStatus)) { return ret }

        return ret
    }

    companion object {
        val LOG by commonLogger()
    }
}