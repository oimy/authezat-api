package soia.authezat.infra.database.configuration.auditor

import java.util.*

object AuditorContextHolder {

    private val currentAuditor = ThreadLocal<UUID?>()

    fun setAuditor(auditor: UUID?) {
        currentAuditor.set(auditor)
    }

    fun getAuditor(): UUID? = currentAuditor.get()

    fun clear() {
        currentAuditor.remove()
    }

}