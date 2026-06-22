package com.devs.departamentopolicial.Veiculo;

import com.devs.departamentopolicial.Exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;

    public VeiculoService(VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    public VeiculoEntity adicionarVeiculo(VeiculoEntity veiculo) {
        return veiculoRepository.save(veiculo);
    }

    public List<VeiculoEntity> listarVeiculos() {
        return veiculoRepository.findAll();
    }

    public VeiculoEntity listarVeiculoPorId(UUID id) {
        return veiculoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Esse Id não corresponde ao Veiculo"));
    }

    public VeiculoEntity listarVeiculoPorPlaca(String placa) {
        VeiculoEntity veiculo = veiculoRepository.findByPlaca(placa);
        if (veiculo == null) {
            throw new NotFoundException("Essa placa não corresponde ao Veiculo");
        }
        return veiculo;
    }

    @Transactional
    public VeiculoEntity atualizarVeiculoPorId(UUID id,VeiculoEntity veiculo) {
        VeiculoEntity veiculoAtualizado = veiculoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Esse Id não corresponde ao Veiculo"));
        if (veiculo.getPlaca() != null) {
            veiculo.setPlaca(veiculoAtualizado.getPlaca());
        }
        if (veiculo.getMarca() != null) {
            veiculo.setMarca(veiculoAtualizado.getMarca());
        }
        if (veiculo.getModelo() != null) {
            veiculo.setModelo(veiculoAtualizado.getModelo());
        }
        if (veiculo.getCor() != null) {
            veiculo.setCor(veiculoAtualizado.getCor());
        }
        if (veiculo.getSituacao() != null) {
            veiculo.setSituacao(veiculoAtualizado.getSituacao());
        }
        return veiculoRepository.save(veiculoAtualizado);
    }
}
