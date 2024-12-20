package com.jjlee.lockmode.redis

import java.util.concurrent.TimeUnit

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class DistributedLock(

    /**
     * 락의 이름
     */
    val key: String,

    /**
     * 락의 시간 단위
     */
    val timeUnit: TimeUnit = TimeUnit.SECONDS,

    /**
     * 락을 기다리는 시간 (default - 5s)
     * 락 획득을 위해 waitTime 만큼 대기한다
     */
    val waitTime: Long = 5L,

    /**
     * 락 임대 시간 (default - 3s)
     * 락을 획득한 이후 leaseTime 이 지나면 락을 해제한다
     */
    val leaseTime: Long = 3L
)

