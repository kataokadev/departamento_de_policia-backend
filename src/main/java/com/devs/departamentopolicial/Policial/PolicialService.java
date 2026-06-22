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

    public PolicialEntity adicionarPolicial(PolicialEntity policialEntity) {
        if (policialEntity.getViatura() == null || policialEntity.getViatura().getId() == null) {
            throw new IllegalArgumentException("É necessário informar uma viatura válida");
        }
        ViaturaEntity viatura = viaturaRepository.findById(policial.getViatura().getId())
                .orElseThrow(() -> new NotFoundException("Esse Id não corresponde a nenhuma viatura"));
        policialEntity.setViatura(viatura);

        return policialRepository.save(policialEntity);
    }

    public List<PolicialEntity> listarPoliciais() {
        return policialRepository.findAll();
    }

    public PolicialEntity listarPoliciaisPorId(UUID id) {
        return policialRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Esse Id não corresponde a nenhum policial"));
    }

    @Transactional
    public PolicialEntity atualizarPoliciaisPorId(UUID id, PolicialEntity policialEntity) {
        PolicialEntity policialAtualizado = policialRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Esse Id não corresponde a nenhum policial"));
        if (policialEntity.getNome() != null) {
            policialAtualizado.setNome(policialEntity.getNome());
        }
        if(policialEntity.getCargo() != null) {
            policialAtualizado.setCargo(policialEntity.getCargo());
        }
        if (policialEntity.getStatus() != null) {
            policialAtualizado.setStatus(policialEntity.getStatus());
        }
        validarPolicial(policialEntity);
        ViaturaEntity viatura = buscarViaturaPorId(policialEntity);

        policialAtualizado.setViatura(viatura);
        return policialRepository.save(policialEntity);
    }

    // metodos privados
    public void validarPolicial(PolicialEntity policial) {
        if (policial.getViatura()  != null || policial.getViatura().getId() != null) {
            throw new IllegalArgumentException("É necessario informar uma viatura válida");
        }
    }

    public ViaturaEntity buscarViaturaPorId(PolicialEntity policial) {
        return viaturaRepository.findById(policial.getViatura().getId())
                .orElseThrow(() -> new NotFoundException("Esse Id não corresponde a nenhum viatura"));
    }
}
