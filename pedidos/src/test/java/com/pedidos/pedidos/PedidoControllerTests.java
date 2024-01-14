package com.pedidos.pedidos;

import com.pedidos.pedidos.controller.PedidoController;
import com.pedidos.pedidos.domain.Pedido;
import com.pedidos.pedidos.dto.PedidoDTO;
import com.pedidos.pedidos.service.PedidoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PedidoControllerTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Mock
    private PedidoService service;

    @InjectMocks
    PedidoController controller;

    @Test
    void cadastrarPedidoTestSucesso(){
        ResponseEntity<ResponseEntity> responseEntity = restTemplate.postForEntity("/api/v1/cadastrar-pedidos", mockPedidos(), ResponseEntity.class);
        Assertions.assertEquals(HttpStatus.CREATED.value(), responseEntity.getStatusCodeValue());
    }

    @Test
    void consultarPedidoTestSucesso() {
        when(service.consultarPedido(anyInt(), any(), anyString(), anyDouble(), anyInt(), anyInt()))
                .thenReturn(mockPedido());

        ResponseEntity responseEntity = controller.consultarPedido(anyInt(), any(), anyString(), anyDouble(), anyInt(), anyInt());
        Pedido pedido = Pedido.class.cast(responseEntity.getBody());
        Assertions.assertNotNull(pedido);
    }

    private List<PedidoDTO> mockPedidos() {
        PedidoDTO pedidoDTO1 = PedidoDTO
                .builder()
                .numeroControle(1)
                .dataCadastro(LocalDate.now())
                .nome("mock 1")
                .valor(1000.00)
                .quantidade(1)
                .codigoCliente(1)
                .build();

        PedidoDTO pedidoDTO2 = PedidoDTO
                .builder()
                .numeroControle(2)
                .dataCadastro(null)
                .nome("mock 2")
                .valor(500.00)
                .quantidade(2)
                .codigoCliente(2)
                .build();

        List<PedidoDTO> list = new ArrayList<>();
        list.add(pedidoDTO1);
        list.add(pedidoDTO2);
        return list;
    }



    private Pedido mockPedido() {
        return Pedido
                .builder()
                .numeroControle(1)
                .dataCadastro(LocalDate.now())
                .nome("mock 1")
                .valor(1000.00)
                .quantidade(1)
                .codigoCliente(1)
                .build();
    }
}
