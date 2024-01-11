package com.pedidos.pedidos.repository;

import com.pedidos.pedidos.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    Pedido findByNumeroControle(Integer numeroControle);

    @Query("SELECT p FROM Pedido p WHERE p.numeroControle = :numeroControle AND p.dataCadastro = :dataCadastro AND" +
            " p.nome = :nome AND p.valor = :valor AND p.quantidade = :quantidade AND p.codigoCliente = :codigoCliente")
    Pedido consultarPedido(Integer numeroControle, LocalDate dataCadastro, String nome, Double valor,
                           Integer quantidade, Integer codigoCliente);
}
