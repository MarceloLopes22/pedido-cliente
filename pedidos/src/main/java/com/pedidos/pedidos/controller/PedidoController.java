package com.pedidos.pedidos.controller;

import com.pedidos.pedidos.domain.Pedido;
import com.pedidos.pedidos.dto.PedidoDTO;
import com.pedidos.pedidos.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "*")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @PostMapping(value = "cadastrar-pedidos")
    public ResponseEntity cadastrarPedido(@RequestBody List<PedidoDTO> pedidos) {
        service.cadastrarPedido(pedidos);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(value = "consultar-pedido/{numeroControle}/{dataCadastro}/{nome}/{valor}/{quantidade}/{codigoCliente}", method = RequestMethod.GET)
    public ResponseEntity consultarPedido (@PathVariable(value = "numeroControle", required = false) Integer numeroControle,
                                           @PathVariable(value = "dataCadastro", required = false) LocalDate dataCadastro,
                                           @PathVariable(value = "nome", required = false) String nome,
                                           @PathVariable(value = "valor", required = false) Double valor,
                                           @PathVariable(value = "quantidade", required = false) Integer quantidade,
                                           @PathVariable(value = "codigoCliente", required = false) Integer codigoCliente) {

        Pedido pedido = service.consultarPedido(numeroControle, dataCadastro, nome, valor, quantidade, codigoCliente);
        return new ResponseEntity(pedido, HttpStatus.OK);
    }

}
