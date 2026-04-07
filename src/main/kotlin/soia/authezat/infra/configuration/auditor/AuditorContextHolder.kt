package soia.authezat.infra.configuration.auditor

object AuditorContextHolder {

    private val currentAuditor = ThreadLocal<String?>()

    fun setAuditor(auditor: String?) {
        currentAuditor.set(auditor)
    }

    fun getAuditor(): String? = currentAuditor.get()

    fun clear() {
        currentAuditor.remove()
    }

}