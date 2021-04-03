package me.fmoths.common

import org.springframework.batch.core.Step
import org.springframework.batch.core.job.builder.FlowBuilder
import org.springframework.batch.core.job.flow.support.SimpleFlow

class SimpleFlowHolder(private val jobName: String) {
    private val stepList by lazy {
        mutableListOf<SimpleFlow>()
    }

    companion object {
        val LOG by commonLogger()
    }

    //TODO:: 굳이 전부 flow로 만들어야 했을까...?
    fun add(step: Step) = apply {
        stepList.add(FlowBuilder<SimpleFlow>(step.name).start(step).build())
    }

    fun build() = FlowBuilder<SimpleFlow>("${jobName}Flow").run {
        stepList.forEachIndexed { index, simpleFlow ->
            if (index == 0) start(simpleFlow)
            else next(simpleFlow)

            LOG.info("STEP::[${simpleFlow.name}] is running...!")
        }
        build()
    }
}