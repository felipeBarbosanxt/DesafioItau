package com.bbsnxt.api_transacao_itau.controller;

import com.bbsnxt.api_transacao_itau.business.services.EstatisticasService;
import com.bbsnxt.api_transacao_itau.controller.dtos.EstatisticaResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estatistica")
@RequiredArgsConstructor
public class EstatisticaController {

    private final EstatisticasService estatisticasService;

    @GetMapping
    public ResponseEntity<EstatisticaResponseDTO> buscarEstatistica(
            @RequestParam(value = "intervaloBusca", required = false, defaultValue = "60") Integer intervaloBusca){

        return ResponseEntity.ok(estatisticasService.calcularEstatisticas(intervaloBusca));
    }

}
