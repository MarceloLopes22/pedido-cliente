package com.pedidos.pedidos.service;

import com.pedidos.pedidos.domain.Pedido;
import com.pedidos.pedidos.dto.PedidoDTO;
import com.pedidos.pedidos.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;
    private Double valorTotal;

    public PedidoService () {
        this.valorTotal = new Double(Double.MIN_VALUE);
    }

    public void cadastrarPedido(List<PedidoDTO> pedidos) {

        if (pedidos.size() > 10) {
            throw new RuntimeException("O arquivo só pode conter no maximo 10 pedidos");
        }

        for (PedidoDTO pedido: pedidos ) {

            Pedido pedidoConsultado = repository.findByNumeroControle(pedido.getNumeroControle());

            if (pedidoConsultado != null) {
                throw new RuntimeException("Não é possivel cadastrar um numero de controle repetido.");
            }

            if (pedido.getDataCadastro() == null){
                pedido.setDataCadastro(LocalDate.now());
            }

            if (pedido.getQuantidade() == null) {
                pedido.setQuantidade(1);
            }

            if (pedido.getQuantidade() >= 5 && pedido.getQuantidade() < 10) {
                double desconto = pedido.getValor() * 5 / 100;
                pedido.setValor(pedido.getValor() - desconto);
            }

            if (pedido.getQuantidade() == 10) {
                double desconto = pedido.getValor() * 10 / 100;
                pedido.setValor(pedido.getValor() - desconto);
            }

            valorTotal = valorTotal + pedido.getValor();
        }

        pedidos.forEach(pedidoDTO -> {
            Pedido pedido = Pedido
                    .builder()
                    .numeroControle(pedidoDTO.getNumeroControle())
                    .dataCadastro(pedidoDTO.getDataCadastro())
                    .nome(pedidoDTO.getNome())
                    .valor(pedidoDTO.getValor())
                    .valorTotal(valorTotal)
                    .quantidade(pedidoDTO.getQuantidade())
                    .codigoCliente(pedidoDTO.getCodigoCliente())
                    .build();

            repository.saveAndFlush(pedido);
        });
    }

    public Pedido consultarPedido(Integer numeroControle, LocalDate dataCadastro, String nome, Double valor,
                                  Integer quantidade, Integer codigoCliente) {
        Pedido pedido = repository.consultarPedido(numeroControle, dataCadastro, nome, valor, quantidade, codigoCliente);
        return pedido;
    }
}
