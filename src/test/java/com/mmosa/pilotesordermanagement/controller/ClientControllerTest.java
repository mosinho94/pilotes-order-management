package com.mmosa.pilotesordermanagement.controller;

import com.mmosa.pilotesordermanagement.controller.client.ClientController;
import com.mmosa.pilotesordermanagement.dto.client.ClientDTO;
import com.mmosa.pilotesordermanagement.service.client.ClientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static com.mmosa.pilotesordermanagement.util.Utils.asJsonString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(ClientController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;

    @Test
    public void testCreateClient() throws Exception {
        ClientDTO clientDTO = createSampleClientDTO("John", "Doe", "123-456-7890");
        given(clientService.saveClient(ArgumentMatchers.any())).willAnswer((invocation -> invocation.getArgument(0)));

        mockMvc.perform(post("/api/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(clientDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdateClient() throws Exception {
        Long clientId = 1L;
        String updatedFirstName = "Jane";
        String updatedLastName = "Smith";
        String updatedPhoneNumber = "987-654-3210";

        ClientDTO updatedClientDTO = createSampleClientDTO(updatedFirstName, updatedLastName, updatedPhoneNumber);

        when(clientService.updateClient(clientId, updatedClientDTO)).thenReturn(updatedClientDTO);

        mockMvc.perform(put("/api/clients/{clientId}", clientId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(updatedClientDTO)))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetClientsByName() throws Exception {
        String name = "John";
        List<ClientDTO> clients = new ArrayList<>();
        when(clientService.findClientByFirstName(name)).thenReturn(clients);

        mockMvc.perform(get("/api/clients")
                        .param("name", name))
                .andExpect(status().isOk());
    }

    private ClientDTO createSampleClientDTO(String firstName, String lastName, String telephoneNumber) {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setFirstName(firstName);
        clientDTO.setLastName(lastName);
        clientDTO.setTelephoneNumber(telephoneNumber);
        return clientDTO;
    }
}
