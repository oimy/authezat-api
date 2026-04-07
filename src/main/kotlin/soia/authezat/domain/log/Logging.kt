package soia.authezat.domain.log

import org.slf4j.LoggerFactory

abstract class Logging {

    val log: Logger = SimpleLogger(logger = LoggerFactory.getLogger(javaClass.enclosingClass))

}