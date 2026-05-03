package soia.authezat.domain.service.server

import jakarta.persistence.EntityNotFoundException
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import soia.authezat.domain.service.server.values.Endpoint
import soia.authezat.infra.database.dolphin.server.*
import soia.authezat.infra.database.dolphin.server.enums.EndpointMethod
import soia.authezat.mock.utils.commit
import kotlin.test.assertEquals

@ExtendWith(MockitoExtension::class)
@TestMethodOrder(MethodOrderer.Default::class)
class EndpointServiceImplTest(
    @Mock private val serverRepository: ServerRepository,
    @Mock private val endpointRepository: EndpointRepository
) {
    private val endpointService: EndpointService = EndpointServiceImpl(
        endpointRepository = endpointRepository,
        serverRepository = serverRepository
    )

    @Nested
    inner class FindAllByServerSrlTest {
        @Test
        @DisplayName("유효한 서버 srl이 주어졌을 때, 해당 서버 srl와 일치하는 Endpoint들을 반환합니다.")
        fun test_10() {
            // given
            val givenServerSrl: Long = serverEntities[0].srl

            // when
            val actualEndpoints: List<Endpoint> = endpointService.findAllByServerSrl(givenServerSrl)

            // then
            val expectEndpointSize = 2
            assertEquals(expectEndpointSize, actualEndpoints.size)
        }

        @Test
        @DisplayName("유효하지 않은 서버 srl이 주어졌을 때, EntityNotFoundException을 일으킵니다.")
        fun test_20() {
            // given
            val givenServerSrl: Long = serverEntities[0].srl * -1

            // when & then
            assertThrows<EntityNotFoundException> {
                endpointService.findAllByServerSrl(givenServerSrl)
            }
        }
    }

    private val serverEntities = listOf(
        ServerEntity(name = "server-0", url = "localhost-0:1234", version = 1).commit(srl = 1L)
    )
    private val serverRepositoryMock = ServerRepositoryMock(serverRepository, serverEntities)
    private val endpointEntities = listOf(
        EndpointEntity(server = serverEntities[0], method = EndpointMethod.GET, path = "/sample-1").commit(srl = 1L),
        EndpointEntity(server = serverEntities[0], method = EndpointMethod.POST, path = "/sample-2").commit(srl = 2L)
    )
    private val endpointRepositoryMock = EndpointRepositoryMock(endpointRepository, endpointEntities)

    @BeforeEach
    fun setMocks() {
        serverRepositoryMock.findById()
        endpointRepositoryMock.findAllByServer()
    }

}