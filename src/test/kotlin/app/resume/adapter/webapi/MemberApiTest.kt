package app.resume.adapter.webapi

import app.resume.adapter.webapi.dto.MemberRegisterResponse
import app.resume.application.member.provided.MemberRegister
import app.resume.application.member.required.MemberRepository
import app.resume.domain.member.MemberFixture
import app.resume.domain.member.MemberStatus
import com.fasterxml.jackson.databind.ObjectMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.assertj.MockMvcTester
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class MemberApiTest(
    val mvcTester: MockMvcTester,
    val objectMapper: ObjectMapper,
    val memberRepository: MemberRepository,
    val memberRegister: MemberRegister,
) {

    @Test
    fun register() {
        val request = MemberFixture.createMemberRegisterRequest()
        val requestJson = objectMapper.writeValueAsString(request)

        val result = mvcTester.post().uri("/api/members")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestJson).exchange()

        assertThat(result)
            .hasStatusOk()
            .bodyJson()
            .hasPathSatisfying("$.memberId") {
                value -> assertThat(value).isNotNull()
            }
            .hasPathSatisfying("$.email") {
                value -> assertThat(value).isEqualTo(request.email)
            }

        val response = objectMapper.readValue(result.response.contentAsString, MemberRegisterResponse::class.java)
        val member = memberRepository.findById(response.memberId) ?: throw NoSuchElementException()

        assertThat(member.email.address).isEqualTo(request.email)
        assertThat(member.nickname).isEqualTo(request.nickname)
        assertThat(member.status).isEqualTo(MemberStatus.PENDING)
    }

    @Test
    fun duplicateEmail() {
        memberRegister.register(MemberFixture.createMemberRegisterRequest())

        val request = MemberFixture.createMemberRegisterRequest()
        val requestJson = objectMapper.writeValueAsString(request)

        val result = mvcTester.post().uri("/api/members")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestJson).exchange()

        assertThat(result)
            .apply { MockMvcResultHandlers.print() }
            .hasStatus(HttpStatus.CONFLICT)
    }
}
