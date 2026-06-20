package com.devs.departamentopolicial.Viatura;

import com.devs.departamentopolicial.Exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ViaturaService {

    private final ViaturaRepository viaturaRepository;

    public ViaturaService(ViaturaRepository viaturaRepository) {
        this.viaturaRepository = viaturaRepository;
    }

    public ViaturaEntity adicionarViatura(ViaturaEntity viaturaEntity) {
        return viaturaRepository.save(viaturaEntity);
    }

    public List<ViaturaEntity> buscarViaturas() {
        return viaturaRepository.findAll();
    }

    public ViaturaEntity buscarViaturaPorId(UUID idViatura) {
        return viaturaRepository.findById(idViatura)
                .orElseThrow(() -> new NotFoundException("Nenhum Viatura encontrada"));
    }

    public ViaturaEntity buscarViaturaPorPlaca(String placa) {
        ViaturaEntity viaturaEntity = viaturaRepository.findByPlaca(placa);
        if(viaturaEntity == null) {
            throw new NotFoundException("Nenhuma viatura encontrada");
        }
        return viaturaEntity;
    }

    public List<ViaturaEntity> buscarViaturasPorModelo(String modelo) {
        List<ViaturaEntity> viaturas = viaturaRepository.findByModelo(modelo);
        if(viaturas.isEmpty()) {
            throw new NotFoundException("Nenhuma viatura encontrada");
        }
        return viaturas;
    }

    public ViaturaEntity atualizarViaturaPorId(UUID id,ViaturaEntity viaturaEntity) {
        ViaturaEntity viaturaAtualizado = viaturaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nenhuma viatura encontrada"));
        if(viaturaEntity.getPlaca() != null){
            viaturaAtualizado.setPlaca(viaturaEntity.getPlaca());
        }
        if(viaturaEntity.getModelo() != null){
            viaturaAtualizado.setModelo(viaturaEntity.getModelo());
        }
        if(viaturaEntity.getMarca() != null){
            viaturaAtualizado.setMarca(viaturaEntity.getMarca());
        }
        if(viaturaEntity.getSituacao() != null){
            viaturaAtualizado.setSituacao(viaturaEntity.getSituacao());
        }
        return viaturaRepository.save(viaturaAtualizado);
    }

}
