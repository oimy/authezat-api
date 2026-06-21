package soia.authezat.app.controller.access

import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import soia.authezat.app.controller.access.payloads.RoleUserRequestFetchUserPayload
import soia.authezat.app.controller.access.payloads.RoleUserRequestPayload
import soia.authezat.app.controller.access.payloads.RoleUserRequestSavePayload
import soia.authezat.domain.service.access.RoleUserRequestService
import soia.authezat.infra.database.configuration.auditor.annotations.*
import soia.authezat.infra.database.dolphin.base.enums.RequestStatus
import java.util.*

@RestController
@RequestMapping("/access/roles")
@Validated
class RoleUserRequestController(
    private val roleUserRequestService: RoleUserRequestService,
) {

    @PostMapping("/{roleSrl}/requests")
    @ResponseStatus(HttpStatus.CREATED)
    @AuditCreatedBy
    fun save(
        @PathVariable roleSrl: Long,
        @RequestBody roleSave: RoleUserRequestSavePayload,
        @Audited createdBy: UUID,
    ) =
        roleUserRequestService.save(
            roleSrl = roleSrl,
            accessType = roleSave.accessType,
            content = roleSave.content,
            createdBy = createdBy
        )

    @GetMapping("/requests/outbound")
    @AuditAccessedBy
    fun getOutboundRequests(
        @RequestParam statuses: Set<RequestStatus>,
        @Audited accessedBy: UUID,
    ): List<RoleUserRequestPayload> =
        roleUserRequestService.findAllByStatusInAndCreatedBy(statuses = statuses, createdBy = accessedBy)
            .map {
                RoleUserRequestPayload(
                    srl = it.srl,
                    roleSrl = it.roleSrl,
                    accessType = it.accessType,
                    content = it.content,
                    status = it.status,
                    reason = it.reason,
                )
            }

    @GetMapping("/requests/inbound")
    @AuditAccessedBy
    fun getInboundRequests(
        @RequestParam status: RequestStatus,
        @Audited accessedBy: UUID,
    ): List<RoleUserRequestFetchUserPayload> =
        roleUserRequestService.findAllByRoleInAndStatusFetchUser(status = status, accessedBy = accessedBy)
            .map {
                RoleUserRequestFetchUserPayload(
                    srl = it.srl,
                    roleSrl = it.roleSrl,
                    userName = it.user.name,
                    accessType = it.accessType,
                    content = it.content,
                    status = it.status,
                    reason = it.reason,
                )
            }

    @PatchMapping("/requests/{requestSrl}/approve")
    @AuditModifiedBy
    fun accept(
        @PathVariable requestSrl: Long,
        @Audited acceptedBy: UUID,
    ) =
        roleUserRequestService.accept(requestSrl = requestSrl, acceptedBy = acceptedBy)

    @PatchMapping("/requests/{requestSrl}/reject")
    @AuditModifiedBy
    fun reject(
        @PathVariable requestSrl: Long,
        @RequestParam reason: String,
        @Audited rejectedBy: UUID,
    ) =
        roleUserRequestService.reject(requestSrl = requestSrl, reason = reason, rejectedBy = rejectedBy)

    @DeleteMapping("/requests/{requestSrl}")
    @AuditDeletedBy
    fun delete(
        @PathVariable requestSrl: Long,
        @Audited deletedBy: UUID,
    ) =
        roleUserRequestService.delete(requestSrl = requestSrl, deletedBy = deletedBy)

}