package app.resume.adapter.integration

import app.resume.domain.shared.Email
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junitpioneer.jupiter.StdIo
import org.junitpioneer.jupiter.StdOut

class DummyEmailSenderTest {
    @Test
    @StdIo
    fun dummyEmailSender(out: StdOut) {
        val dummyEmailSender = DummyEmailSender()

        dummyEmailSender.send(Email("y2gcoder@gmail.com"), "subject", "body")

        assertThat(out.capturedLines()[0])
            .isEqualTo("DummyEmailSender send email: Email(address=y2gcoder@gmail.com)")
    }
}