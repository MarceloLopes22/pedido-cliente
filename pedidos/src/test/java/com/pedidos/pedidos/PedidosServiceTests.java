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
	void cadastrarPedidoTestErroQuantidadePedidos() {
		Assertions.assertThatThrownBy(() -> service.cadastrarPedido(mockPedidosError()))
				.hasMessage("O arquivo só pode conter no maximo 10 pedidos");
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

	private List<PedidoDTO> mockPedidosError() {
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

		PedidoDTO pedidoDTO3 = PedidoDTO
				.builder()
				.numeroControle(3)
				.dataCadastro(null)
				.nome("mock 3")
				.valor(500.00)
				.quantidade(3)
				.codigoCliente(3)
				.build();

		PedidoDTO pedidoDTO4 = PedidoDTO
				.builder()
				.numeroControle(4)
				.dataCadastro(null)
				.nome("mock 4")
				.valor(500.00)
				.quantidade(4)
				.codigoCliente(4)
				.build();

		PedidoDTO pedidoDTO5 = PedidoDTO
				.builder()
				.numeroControle(5)
				.dataCadastro(null)
				.nome("mock 5")
				.valor(500.00)
				.quantidade(5)
				.codigoCliente(5)
				.build();

		PedidoDTO pedidoDTO6 = PedidoDTO
				.builder()
				.numeroControle(6)
				.dataCadastro(null)
				.nome("mock 6")
				.valor(500.00)
				.quantidade(6)
				.codigoCliente(6)
				.build();

		PedidoDTO pedidoDTO7 = PedidoDTO
				.builder()
				.numeroControle(7)
				.dataCadastro(null)
				.nome("mock 7")
				.valor(500.00)
				.quantidade(7)
				.codigoCliente(7)
				.build();

		PedidoDTO pedidoDTO8 = PedidoDTO
				.builder()
				.numeroControle(8)
				.dataCadastro(null)
				.nome("mock 8")
				.valor(500.00)
				.quantidade(8)
				.codigoCliente(8)
				.build();

		PedidoDTO pedidoDTO9 = PedidoDTO
				.builder()
				.numeroControle(9)
				.dataCadastro(null)
				.nome("mock 9")
				.valor(500.00)
				.quantidade(9)
				.codigoCliente(9)
				.build();

		PedidoDTO pedidoDTO10 = PedidoDTO
				.builder()
				.numeroControle(10)
				.dataCadastro(null)
				.nome("mock 10")
				.valor(500.00)
				.quantidade(10)
				.codigoCliente(10)
				.build();

		PedidoDTO pedidoDTO11 = PedidoDTO
				.builder()
				.numeroControle(11)
				.dataCadastro(null)
				.nome("mock 11")
				.valor(500.00)
				.quantidade(11)
				.codigoCliente(11)
				.build();

		List<PedidoDTO> list = new ArrayList<>();
		list.add(pedidoDTO1);
		list.add(pedidoDTO2);
		list.add(pedidoDTO3);
		list.add(pedidoDTO4);
		list.add(pedidoDTO5);
		list.add(pedidoDTO6);
		list.add(pedidoDTO7);
		list.add(pedidoDTO8);
		list.add(pedidoDTO9);
		list.add(pedidoDTO10);
		list.add(pedidoDTO11);
		return list;
	}

}
