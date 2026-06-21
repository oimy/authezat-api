package soia.authezat.domain.service.client

import org.springframework.stereotype.Service
import soia.authezat.infra.database.dolphin.account.ClientRepository

@Service
class ClientServiceImpl(
    private val clientRepository: ClientRepository,
) :
    ClientService {

    override fun existByNameAndKey(name: String, key: String): Boolean {
        return clientRepository.existsByNameAndFirstKey(name = name, firstKey = key)
            .or(clientRepository.existsByNameAndSecondKey(name = name, secondKey = key))
    }

}