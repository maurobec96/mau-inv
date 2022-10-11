package com.mbec.mau_inv.rest;

import com.mbec.mau_inv.model.ClientDTO;
import com.mbec.mau_inv.service.ClientService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/clients", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientResource {

    private final ClientService clientService;

    public ClientResource(final ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<ClientDTO>> getAllClients() {
        return ResponseEntity.ok(clientService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClient(@PathVariable final Long id) {
        return ResponseEntity.ok(clientService.get(id));
    }

    @PostMapping
    public ResponseEntity<Long> createClient(@RequestBody @Valid final ClientDTO clientDTO) {
        return new ResponseEntity<>(clientService.create(clientDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateClient(@PathVariable final Long id,
            @RequestBody @Valid final ClientDTO clientDTO) {
        clientService.update(id, clientDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable final Long id) {
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
