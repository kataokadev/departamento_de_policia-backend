package com.devs.departamentopolicial.Policial;

import com.devs.departamentopolicial.Exceptions.NotFoundException;
import com.devs.departamentopolicial.Viatura.ViaturaEntity;
import com.devs.departamentopolicial.Viatura.ViaturaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class PolicialService {

    private final PolicialRepository policialRepository;
    private final ViaturaRepository viaturaRepository;

    public PolicialService(PolicialRepository policialRepository, ViaturaRepository viaturaRepository) {
        this.policialRepository = policialRepository;
        this.viaturaRepository = viaturaRepository;
    }

    public PolicialEntity addPolicial(PolicialEntity policial) {
        if (policial.getViatura() == null || policial.getViatura().getId() == null) {
            throw new IllegalArgumentException("É necessário informar uma viatura válida");
        }
        ViaturaEntity viatura = viaturaRepository.findById(policial.getViatura().getId())
                .orElseThrow(() -> new NotFoundException("Esse Id não corresponde a nenhuma viatura"));
        policial.setViatura(viatura);

        return policialRepository.save(policial);
    }

    public List<PolicialEntity> buscarPoliciais() {
        return policialRepository.findAll();
    }

    public PolicialEntity buscarPoliciaisPorId(UUID id) {
        return policialRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Esse Id não corresponde a nenhum policial"));
    }

    @Transactional
    public PolicialEntity atualizarPoliciaisPorId(UUID id, PolicialEntity policial) {
        PolicialEntity policialEntity = policialRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Esse Id não corresponde a nenhum policial"));
        if (policial.getNome() != null) {
            policialEntity.setNome(policial.getNome());
        }
        if(policial.getCargo() != null) {
            policialEntity.setCargo(policial.getCargo());
        }
        if (policial.getStatus() != null) {
            policialEntity.setStatus(policial.getStatus());
        }
        if (policial.getViatura()  != null && policial.getViatura().getId() != null) {
            ViaturaEntity viatura = viaturaRepository.findById(policial.getViatura().getId())
                    .orElseThrow(() -> new NotFoundException("Esse Id não corresponde a nenhum viatura"));
                    policialEntity.setViatura(viatura);
        }
        return policialRepository.save(policialEntity);
    }
}
