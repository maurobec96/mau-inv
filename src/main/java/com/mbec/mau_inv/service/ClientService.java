package com.mbec.mau_inv.service;

import com.mbec.mau_inv.domain.Client;
import com.mbec.mau_inv.model.ClientDTO;
import com.mbec.mau_inv.repos.ClientRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(final ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<ClientDTO> findAll() {
        return clientRepository.findAll(Sort.by("id"))
                .stream()
                .map(client -> mapToDTO(client, new ClientDTO()))
                .collect(Collectors.toList());
    }

    public ClientDTO get(final Long id) {
        return clientRepository.findById(id)
                .map(client -> mapToDTO(client, new ClientDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final ClientDTO clientDTO) {
        final Client client = new Client();
        mapToEntity(clientDTO, client);
        return clientRepository.save(client).getId();
    }

    public void update(final Long id, final ClientDTO clientDTO) {
        final Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(clientDTO, client);
        clientRepository.save(client);
    }

    public void delete(final Long id) {
        clientRepository.deleteById(id);
    }

    private ClientDTO mapToDTO(final Client client, final ClientDTO clientDTO) {
        clientDTO.setId(client.getId());
        clientDTO.setClientName(client.getClientName());
        clientDTO.setAddress(client.getAddress());
        clientDTO.setPhoneNumber(client.getPhoneNumber());
        clientDTO.setEmail(client.getEmail());
        return clientDTO;
    }

    private Client mapToEntity(final ClientDTO clientDTO, final Client client) {
        client.setClientName(clientDTO.getClientName());
        client.setAddress(clientDTO.getAddress());
        client.setPhoneNumber(clientDTO.getPhoneNumber());
        client.setEmail(clientDTO.getEmail());
        return client;
    }

}
