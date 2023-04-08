package jp.musubi.controller

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.servlet.ViewResolver
import org.springframework.web.servlet.view.InternalResourceViewResolver

@WebMvcTest(controllers = [MsbAuthController::class])
class MsbAuthControllerTests {

    @InjectMocks
    lateinit var msbAuthController:MsbAuthController
    private var mockMvc:MockMvc? = null

    // ViewResolverの設定
    private fun viewResolver():ViewResolver {
        val viewResolver = InternalResourceViewResolver()
        viewResolver.setPrefix("classpath:templates/")
        viewResolver.setSuffix(".html")
        return viewResolver
    }

    @BeforeEach
    fun setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(msbAuthController).setViewResolvers(viewResolver()).build()
    }

    @Test
    fun getTest() {
        val requestBuilder = MockMvcRequestBuilders.get("/auth/login")
        mockMvc?.perform(requestBuilder)
                ?.andExpect(status().isOk)
                ?.andExpect(view().name("auth/login"))
                ?.andExpect(model().hasNoErrors())
                ?.andExpect(model().attribute("message", "ログイン"))
    }
}