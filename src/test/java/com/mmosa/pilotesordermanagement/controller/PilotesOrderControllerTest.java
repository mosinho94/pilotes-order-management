package com.mmosa.pilotesordermanagement.controller;

import com.mmosa.pilotesordermanagement.controller.order.PilotesOrderController;
import com.mmosa.pilotesordermanagement.dto.client.ClientDTO;
import com.mmosa.pilotesordermanagement.dto.order.PilotesOrderDTO;
import com.mmosa.pilotesordermanagement.service.order.PilotesOrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.mmosa.pilotesordermanagement.util.Utils.asJsonString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(PilotesOrderController.class)
@AutoConfigureMockMvc(addFilters = false)
class PilotesOrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PilotesOrderService orderService;

    @Test
    void testCreateOrder() throws Exception {
        Long clientId = 1L;
        PilotesOrderDTO orderDTO = createSampleOrderDTO();

        mockMvc.perform(post("/api/orders/{clientId}", clientId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(orderDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    void testUpdateOrder() throws Exception {
        Long orderId = 1L;
        PilotesOrderDTO orderDTO = createSampleOrderDTO();
        when(orderService.updateOrder(orderId, orderDTO)).thenReturn(orderDTO);

        mockMvc.perform(put("/api/orders/{orderId}", orderId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(orderDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteOrder() throws Exception {
        Long orderId = 1L;
        doNothing().when(orderService).deleteOrder(orderId);

        mockMvc.perform(delete("/api/orders/{orderId}", orderId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private PilotesOrderDTO createSampleOrderDTO() {
        PilotesOrderDTO orderDTO = new PilotesOrderDTO();
        orderDTO.setDeliveryAddress("123 Main St");
        orderDTO.setNumberOfPilotes(10);
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setFirstName("John");
        clientDTO.setLastName("Doe");
        clientDTO.setTelephoneNumber("123-456-7890");
        orderDTO.setClient(clientDTO);
        return orderDTO;
    }
}
