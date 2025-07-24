package app.resume.domain.member

import app.resume.domain.AbstractEntity
import jakarta.persistence.Entity
import java.time.Instant

@Entity
class MemberDetail(
    var registeredAt: Instant = Instant.now(),
    var activatedAt: Instant? = null,
    var deactivatedAt: Instant? = null,
) : AbstractEntity() {
    companion object {
        fun create(): MemberDetail {
            return MemberDetail()
        }
    }

    fun activate(activatedAt: Instant) {
        check(this.activatedAt == null) {
            "이미 activatedAt은 설정되었습니다"
        }

        this.activatedAt = activatedAt
    }

    fun deactivate(deactivatedAt: Instant) {
        check(this.deactivatedAt == null) {
            "이미 deactivatedAt은 설정되었습니다"
        }

        this.deactivatedAt = deactivatedAt
    }
}