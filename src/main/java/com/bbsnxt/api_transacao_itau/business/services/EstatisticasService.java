package com.bbsnxt.api_transacao_itau.business.services;

import com.bbsnxt.api_transacao_itau.controller.dtos.EstatisticaResponseDTO;
import com.bbsnxt.api_transacao_itau.controller.dtos.TransacaoRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EstatisticasService {

    public final TransacaoService transacaoService;

    public EstatisticaResponseDTO calcularEstatisticas(Integer intervaloBusca) {

        log.info("Iniciada busca de estatisticas de transações pelo periodo de tempo" + intervaloBusca);
        List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacoes(intervaloBusca);

        if (transacoes.isEmpty()) {
            return new EstatisticaResponseDTO(0L, 0.0, 0.0, 0.0, 0.0);
        }

        DoubleSummaryStatistics estatisticasTransacoes = transacoes.stream().mapToDouble(TransacaoRequestDTO::valor)
                .summaryStatistics();
        log.info("Estatisticas retornadas com sucesso.");
        return new EstatisticaResponseDTO(estatisticasTransacoes.getCount(),
                estatisticasTransacoes.getSum(),
                estatisticasTransacoes.getAverage(),
                estatisticasTransacoes.getMin(),
                estatisticasTransacoes.getMax());
    }
}
