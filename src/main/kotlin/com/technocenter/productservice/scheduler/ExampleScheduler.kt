package com.technocenter.productservice.scheduler

import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class ExampleScheduler {
    val log = LoggerFactory.getLogger(ExampleScheduler::class.java)
    //scheduler 5 detik sekali
    @Scheduled(fixedRate = 5000) //ms
    fun schedulerForEvery5Sec(){
        log.info("FIXED RATE schedulerForEvery5Sec")
    }

    //CRON = *  *   *       *       *       *
    //      Sec Min Hour    DAY     MONTH   STRING DAY
    @Scheduled(cron = "*/5 * * * * *") //ms
    fun schedulerCronForEvery5Sec(){
        log.info("CRON schedulerForEvery5Sec")
    }
}