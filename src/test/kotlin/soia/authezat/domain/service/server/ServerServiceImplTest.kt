package soia.authezat.domain.service.server

import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import soia.authezat.domain.service.server.values.Server
import soia.authezat.infra.database.dolphin.server.ServerEntity
import soia.authezat.infra.database.dolphin.server.ServerRepository
import soia.authezat.infra.database.dolphin.server.ServerRepositoryMock
import java.time.LocalDateTime
import kotlin.test.assertEquals

@ExtendWith(MockitoExtension::class)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class ServerServiceImplTest(
    @Mock private val serverRepository: ServerRepository,
) {

    @Test
    @Order(10)
    @DisplayName("-, findAll을 호출하면, 전체 서버들을 반환합니다.")
    fun test_10_findAll() {
        // given

        // when
        val actualServers: List<Server> = serverService.findAll()

        // then
        val expectServerCount = serverEntities.size
        assertEquals(expectServerCount, actualServers.size)
    }

    private val serverService = ServerServiceImpl(serverRepository)

    private val serverEntities = listOf(
        ServerEntity(name = "server-0", url = "localhost-0:1234", version = 1).apply {
            this.createdBy = "created-by-0"
            this.createdAt = LocalDateTime.now()
            this.modifiedBy = "modified-by-0"
            this.modifiedAt = LocalDateTime.now()
        }
    )
    private val serverRepositoryMock = ServerRepositoryMock(serverRepository, serverEntities)

    @BeforeEach
    fun setMocks() {
        serverRepositoryMock.findAll()
    }

}