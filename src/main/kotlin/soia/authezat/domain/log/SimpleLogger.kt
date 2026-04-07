package soia.authezat.domain.log

import org.slf4j.Logger
import soia.authezat.domain.log.Logger as InternalLogger

class SimpleLogger(private val logger: Logger) : InternalLogger {

    override fun debug(callMessage: () -> Any?) {
        if (logger.isDebugEnabled) {
            logger.debug(callMessage().toString())
        }
    }

    override fun info(callMessage: () -> Any?) {
        if (logger.isInfoEnabled) {
            logger.info(callMessage().toString())
        }
    }

    override fun warn(callMessage: () -> Any?) {
        if (logger.isWarnEnabled) {
            logger.warn(callMessage().toString())
        }
    }

    override fun error(callMessage: () -> Any?) {
        logger.error(callMessage().toString())
    }

}