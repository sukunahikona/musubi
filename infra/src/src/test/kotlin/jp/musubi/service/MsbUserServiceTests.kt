package jp.musubi.service

import com.nhaarman.mockitokotlin2.whenever
import jp.musubi.mapper.MsbUserMapper
import jp.musubi.model.MsbUser
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@WebMvcTest
class MsbUserServiceTests {

    @Mock
    lateinit var msbUserMapper: MsbUserMapper
    @InjectMocks
    lateinit var msbUserService: MsbUserService

    @BeforeEach
    fun setup() {
        whenever(msbUserMapper.findAll()).thenReturn(
                listOf(
                        MsbUser(id="1",name="AAA", password = "AAA", role = 1),
                        MsbUser(id="2",name="BBB", password = "BBB", role = 2),
                        MsbUser(id="3",name="CCC", password = "CCC", role = 3),
                )
        )
    }

    @Test
    fun getFindAllTest() {
        val list:List<MsbUser> = msbUserService.findAll()
        assertEquals(3, list.size)
    }
}