package soia.authezat.infra.database.configuration.auditor.annotations

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class AuditDeletedBy(
    val required: Boolean = true,
    val headerName: String = "X-Deleted-By",
)