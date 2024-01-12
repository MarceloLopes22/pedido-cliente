package com.pedidos.pedidos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PedidoDTO {
    private Integer numeroControle;
    private LocalDate dataCadastro;
    private String nome;
    private Double valor;
    private Integer quantidade;
    private Integer codigoCliente;
}
