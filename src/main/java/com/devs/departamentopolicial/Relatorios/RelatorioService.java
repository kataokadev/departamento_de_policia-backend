package com.devs.departamentopolicial.Relatorios;

import com.devs.departamentopolicial.Exceptions.NotFoundException;
import com.devs.departamentopolicial.Ocorrencia.OcorrenciaEntity;
import com.devs.departamentopolicial.Ocorrencia.OcorrenciaRepository;
import com.devs.departamentopolicial.Policial.PolicialEntity;
import com.devs.departamentopolicial.Policial.PolicialRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RelatorioService {

    private final RelatorioRepository relatorioRepository;
    private final PolicialRepository policialRepository;
    private final OcorrenciaRepository ocorrenciaRepository;

    public RelatorioService(RelatorioRepository relatorioRepository, PolicialRepository policialRepository, OcorrenciaRepository ocorrenciaRepository) {
        this.relatorioRepository = relatorioRepository;
        this.policialRepository = policialRepository;
        this.ocorrenciaRepository = ocorrenciaRepository;
    }

    public RelatorioEntity salvarRelatorio(RelatorioEntity relatorioEntity) {
        validarRelatorio(relatorioEntity);

        PolicialEntity policial = buscarPolicialPorId(relatorioEntity);
        OcorrenciaEntity ocorrencia = buscarOcorrenciaPorId(relatorioEntity);

        relatorioEntity.setOcorrencia(ocorrencia);
        relatorioEntity.setPolicial(policial);

        return relatorioRepository.save(relatorioEntity);
    }

    public List<RelatorioEntity> listarRelatorios() {
        return relatorioRepository.findAll();
    }

    public RelatorioEntity listarRelatorioPorId(UUID id) {
        return relatorioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Esse Id não corresponde a nenhum relatorio"));
    }

    public RelatorioEntity atualizarRelatorioPorId(UUID id, RelatorioEntity relatorioEntity) {
        RelatorioEntity relatorioAtualizado = relatorioRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Esse Id não corresponde a nenhum relatorio"));
        if(relatorioEntity.getDescricao() != null){
            relatorioAtualizado.setDescricao(relatorioEntity.getDescricao());
        }
        validarRelatorio(relatorioEntity);

        PolicialEntity policial = buscarPolicialPorId(relatorioEntity);
        OcorrenciaEntity ocorrencia = buscarOcorrenciaPorId(relatorioEntity);

        relatorioAtualizado.setPolicial(policial);
        relatorioAtualizado.setOcorrencia(ocorrencia);

        return relatorioRepository.save(relatorioAtualizado);
    }



    // Metodos privados

    private void validarRelatorio(RelatorioEntity relatorioEntity) {
        if(relatorioEntity.getPolicial() == null || relatorioEntity.getPolicial().getId() == null) {
            throw new IllegalArgumentException("Ë necessario informar um policial valido");
        }

        if(relatorioEntity.getOcorrencia() == null ||  relatorioEntity.getOcorrencia().getId() == null) {
            throw new IllegalArgumentException("É necessario informar uma ocorrencia valido");
        }
    }

    private PolicialEntity buscarPolicialPorId(RelatorioEntity relatorioEntity) {
       return policialRepository.findById(relatorioEntity.getPolicial().getId())
                .orElseThrow(() -> new NotFoundException("Esse ID não corresponde a nenhum policial válido"));
    }

    private OcorrenciaEntity buscarOcorrenciaPorId(RelatorioEntity relatorioEntity) {
        return ocorrenciaRepository.findById(relatorioEntity.getOcorrencia().getId())
                .orElseThrow(() -> new NotFoundException("Esse ID não corresponde a nenhuma ocorrência válida"));
    }

}
