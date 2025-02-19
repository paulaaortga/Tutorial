package com.ccsw.tutorial.client;

import com.ccsw.tutorial.client.model.Client;
import com.ccsw.tutorial.client.model.ClientDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ccsw
 *
 */
@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Override
    public Client get(Long id) {

        return this.clientRepository.findById(id).orElse(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Client> findAll() {

        return (List<Client>) this.clientRepository.findAll();
    }

    /**
     * {@inheritDoc}
     * && client.getName() != dto.getName()
     *  client = this.clientRepository.findByName(dto.getName());
     */
    @Override
    public void save(Long id, ClientDto dto) throws Exception {

        Client client;
        Boolean exists;

        if (id == null) {
            exists = this.clientRepository.existsByName(dto.getName());
            if (!exists) {
                client = new Client();
                client.setName(dto.getName());
                this.clientRepository.save(client);
            }
            System.out.println("Hay un usuario con el mismo nombre");
        } else {
            client = this.clientRepository.findById(id).orElse(null);
            client.setName(dto.getName());
            this.clientRepository.save(client);
        }
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) throws Exception {

        if (this.clientRepository.findById(id).orElse(null) == null) {
            throw new Exception("Not exists");
        }

        this.clientRepository.deleteById(id);
    }

}