package soia.authezat.infra.configuration.auditor.annotations

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class AuditModifiedBy(
    val required: Boolean = true,
    val headerName: String = "X-Modified-By",
)