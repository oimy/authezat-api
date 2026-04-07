package soia.authezat.infra.database.dolphin.base

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override val srl: Long = 0L,
) :
    Entity {

    @CreatedBy
    @Column(updatable = false)
    lateinit var createdBy: String

    @LastModifiedBy
    lateinit var modifiedBy: String

    lateinit var createdAt: LocalDateTime
    lateinit var modifiedAt: LocalDateTime

    @PrePersist
    override fun create() {
        this.createdAt = LocalDateTime.now()
        this.modifiedAt = createdAt
    }

    @PreUpdate
    override fun modify() {
        this.modifiedAt = LocalDateTime.now()
    }

}