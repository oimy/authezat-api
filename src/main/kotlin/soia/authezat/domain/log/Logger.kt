package soia.authezat.domain.log

interface Logger {

    fun debug(callMessage: () -> Any?)

    fun info(callMessage: () -> Any?)

    fun warn(callMessage: () -> Any?)

    fun error(callMessage: () -> Any?)

}