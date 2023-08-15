package com.mmosa.pilotesordermanagement.controller.client;

import com.mmosa.pilotesordermanagement.dto.client.ClientDTO;
import com.mmosa.pilotesordermanagement.service.client.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@Tag(name = "Client", description = "Client management APIs")
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create new client")
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDTO createClient(@RequestBody @Valid ClientDTO clientDTO) {
        return clientService.saveClient(clientDTO);
    }

    @PutMapping(value = "/{clientId}")
    @Operation(summary = "Update a client by Id")
    @ResponseStatus(HttpStatus.OK)
    public ClientDTO updateClient(@PathVariable Long clientId, @RequestBody @Valid ClientDTO updatedClientDTO) {
        return clientService.updateClient(clientId, updatedClientDTO);
    }

    @GetMapping
    @Operation(summary = "Retrieve client by name")
    @ResponseStatus(HttpStatus.OK)
    public List<ClientDTO> getClientsByName(@RequestParam(required = false) String name) {
        List<ClientDTO> clients = (name != null && !name.isBlank())
                ? clientService.findClientByFirstName(name)
                : clientService.getAllClients();

        return clients;
    }
}
