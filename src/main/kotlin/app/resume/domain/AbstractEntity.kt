package app.resume.domain

import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import org.hibernate.proxy.HibernateProxy

@MappedSuperclass
abstract class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L

    final override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null) return false
        val otherEffectiveClass = getEffectiveClass(other)
        val thisEffectiveClass = getEffectiveClass(this)
        if (thisEffectiveClass != otherEffectiveClass) return false
        other as AbstractEntity

        return id != 0L && id == other.id
    }

    override fun hashCode(): Int = getEffectiveClass(this).hashCode()

    private fun getEffectiveClass(obj: Any?): Class<*> =
        if (obj is HibernateProxy) (this as HibernateProxy).hibernateLazyInitializer.persistentClass else this.javaClass
}