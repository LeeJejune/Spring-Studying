package com.jjlee.lockmode.redis

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.reflect.MethodSignature
import org.redisson.api.RLock
import org.redisson.api.RedissonClient
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Aspect
@Component
class DistributedLockAop(
    private val redissonClient: RedissonClient,
    private val aopForTransaction: AopForTransaction
) {
    private val log = LoggerFactory.getLogger(this.javaClass)

    companion object {
        private const val REDISSON_LOCK_PREFIX = "LOCK:"
    }

    @Around("@annotation(DistributedLock)")
    @Throws(Throwable::class)
    fun lock(joinPoint: ProceedingJoinPoint): Any? {
        val signature = joinPoint.signature as MethodSignature
        val method = signature.method
        val distributedLock = method.getAnnotation(DistributedLock::class.java)

        val key = REDISSON_LOCK_PREFIX + CustomSpringELParser.getDynamicValue(signature.parameterNames, joinPoint.args, distributedLock.key)
        val rLock: RLock = redissonClient.getLock(key)
        log.info("Redisson Lock : ${method.name} + key=$key + rlock=$rLock" )

        return try {
            val available = rLock.tryLock(distributedLock.waitTime, distributedLock.leaseTime, distributedLock.timeUnit)
            if (!available) {
                log.warn("Failed to acquire lock for key: $key")
                throw InterruptedException("Failed to acquire lock for key: $key")
            }
            aopForTransaction.proceed(joinPoint)
        } catch (e: InterruptedException) {
            throw InterruptedException()
        } finally {
            try {
                rLock.unlock()
                log.info("Redisson Lock UnLock : ${method.name} + key=$key" )
            } catch (e: IllegalMonitorStateException) {
                log.info("Redisson Lock Already UnLock : ${method.name} + key=$key" )
            }
        }
    }
}

