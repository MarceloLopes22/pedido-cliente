package com.pedidos.pedidos.dto;

import jakarta.validation.constraints.Max;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {
    private Integer numeroControle;
    private LocalDate dataCadastro;
    private String nome;
    private Double valor;
    private Integer quantidade;
    private Integer codigoCliente;
}
