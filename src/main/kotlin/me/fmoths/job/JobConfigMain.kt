package me.fmoths.job

import me.fmoths.common.JobInterface
import me.fmoths.common.SimpleFlowHolder
import me.fmoths.constants.BatchArgument
import me.fmoths.constants.JobNames
import me.fmoths.step.StepMain
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Configuration

@Configuration
class JobConfigMain @Autowired constructor(
    jobBuilderFactory: JobBuilderFactory,
    jobLauncher: JobLauncher,
    batchArgument: BatchArgument,
    @Qualifier(StepMain.STEP_NAME)
    private val stepMain: Step
): JobInterface(jobLauncher, jobBuilderFactory, batchArgument, JobNames.MAIN){
    override fun getJobFlow() = SimpleFlowHolder(jobName)
        .add(stepMain)
}