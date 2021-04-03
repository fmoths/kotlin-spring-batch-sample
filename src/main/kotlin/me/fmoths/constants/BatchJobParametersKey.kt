package me.fmoths.constants

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import java.time.LocalDate

object BatchArgumentKey {
    const val JOBNAME = "job.param.name"
    const val CRITERIAL_DATE = "job.param.criterialDate"
}

object JobNames {
    const val DEFAULT = ""
    const val MAIN = "main"
}

@Configuration
class BatchArgument {
    @Value("\${${BatchArgumentKey.CRITERIAL_DATE}}")
    private val _criterialDate: String = ""

    val criterialDate : LocalDate by lazy {
        if(_criterialDate.isEmpty()) LocalDate.now().minusDays(1)
        else LocalDate.parse(_criterialDate)
    }
}

object JobParameterKey {
    const val CRITERIAL_DATE = "criterialDate"
}

typealias JobParam = Map<String, Any>
val JobParam.criterialDate get() = this.get(JobParameterKey.CRITERIAL_DATE) as LocalDate