package com.bbsnxt.api_transacao_itau.business.services;

import com.bbsnxt.api_transacao_itau.controller.dtos.TransacaoRequestDTO;
import com.bbsnxt.api_transacao_itau.infrastructure.exceptions.UnprocesableEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransacaoService {

    private final List<TransacaoRequestDTO> listaTransacoes = new ArrayList<>();

    public void adicionarTransacoes(TransacaoRequestDTO dto){
        log.info("Iniciado o processo de gravação da transação: {}", dto);


        if(dto.dataHora().isAfter(OffsetDateTime.now())){
            log.error("Data e hora maiores que a data e hora atual.");
            throw new UnprocesableEntity("Data e hora maiores que a data e hora atual.");
        }
        if(dto.valor() < 0){
            log.error("Valor não pode ser menor do que zero");
            throw new UnprocesableEntity("Valor não pode ser menor do que zero.");
        }

        listaTransacoes.add(dto);
        log.info("Transações adicionadas com sucesso.");
    }

    public void limparTransacoes(){
        log.info("Iniciado processamento para deletar transações.");
        listaTransacoes.clear();
        log.info("Transações deletadas com sucesso.");
    }

    public List<TransacaoRequestDTO> buscarTransacoes(Integer intervaloBusca){
        log.info("Iniciada busca de transações nos últimos {} segundos", intervaloBusca );

        OffsetDateTime dataHoraIntervalo = OffsetDateTime.now().minusSeconds(intervaloBusca);

        List<TransacaoRequestDTO> resultado = listaTransacoes.stream().filter(transacao -> transacao.dataHora().isAfter(dataHoraIntervalo)).toList();

        log.info("Busca concluída com {} transações encontradas.", resultado.size());
        return resultado;
    }
}
