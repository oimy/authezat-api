package soia.authezat.infra.database.dolphin.base

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class ImmutableBaseEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override val srl: Long = Long.MIN_VALUE
) :
    Entity {

    @CreatedBy
    @Column(updatable = false)
    lateinit var createdBy: String

    lateinit var createdAt: LocalDateTime

    @PrePersist
    override fun create() {
        this.createdAt = LocalDateTime.now()
    }

    @PreUpdate
    override fun modify() {
        throw IllegalStateException("modification is forbid")
    }

}