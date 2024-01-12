package com.pedidos.pedidos;

import com.pedidos.pedidos.domain.Pedido;
import com.pedidos.pedidos.dto.PedidoDTO;
import com.pedidos.pedidos.repository.PedidoRepository;
import com.pedidos.pedidos.service.PedidoService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@SpringBootTest
class PedidosServiceTests {

	@Mock
	PedidoRepository repository;

	@InjectMocks
	PedidoService service;
	@Test
	void cadastrarPedidoTestSucesso() {

		service.cadastrarPedido(mockListaPedido());
		verify(repository, times(2)).saveAndFlush(any(Pedido.class));
	}

	@Test
	void cadastrarPedidoTestErro() {

		when(repository.findByNumeroControle(anyInt())).thenReturn(mockPedido());
		Assertions.assertThatThrownBy(() -> service.cadastrarPedido(mockListaPedido()))
				.hasMessage("Não é possivel cadastrar um numero de controle repetido.");
	}

	@Test
	void consultarPedidoTestSucesso(){
		when(repository.consultarPedido(anyInt(), any(), anyString(), anyDouble(), anyInt(), anyInt()))
				.thenReturn(mockPedido());
		Pedido pedido = service.consultarPedido(anyInt(), any(), anyString(), anyDouble(), anyInt(), anyInt());
		assertNotNull(pedido);
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

	private List<PedidoDTO> mockListaPedido() {
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

}
