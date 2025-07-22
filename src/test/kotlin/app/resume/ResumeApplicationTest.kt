package app.resume

import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.springframework.boot.SpringApplication
import org.springframework.context.ConfigurableApplicationContext

class ResumeApplicationTest {
    @Test
    fun run() {
        val args = arrayOf<String>()
        val mockContext = mock(ConfigurableApplicationContext::class.java)

        Mockito.mockStatic(SpringApplication::class.java).use { mocked ->
            mocked.`when`<ConfigurableApplicationContext> {
                SpringApplication.run(ResumeApplication::class.java, *args)
            }.thenReturn(mockContext)

            main(args)

            mocked.verify {
                SpringApplication.run(ResumeApplication::class.java, *args)
            }
        }
    }
}