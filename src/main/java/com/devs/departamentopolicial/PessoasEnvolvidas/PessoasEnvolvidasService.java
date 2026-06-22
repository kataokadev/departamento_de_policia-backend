package com.devs.departamentopolicial.PessoasEnvolvidas;

import com.devs.departamentopolicial.Exceptions.NotFoundException;
import com.devs.departamentopolicial.Veiculo.VeiculoEntity;
import com.devs.departamentopolicial.Veiculo.VeiculoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PessoasEnvolvidasService {

    private final PessoasEnvolvidasRepository pessoasEnvolvidasRepository;
    private final VeiculoRepository veiculoRepository;

    public PessoasEnvolvidasService(PessoasEnvolvidasRepository pessoasEnvolvidasRepository, VeiculoRepository veiculoRepository) {
        this.pessoasEnvolvidasRepository = pessoasEnvolvidasRepository;
        this.veiculoRepository = veiculoRepository;
    }

    public PessoasEnvolvidasEntity adicionarPessoas(PessoasEnvolvidasEntity pessoasEnvolvidasEntity) {
        validarVeiculo(pessoasEnvolvidasEntity);
        VeiculoEntity veiculo = buscarVeiculoPorId(pessoasEnvolvidasEntity);
        pessoasEnvolvidasEntity.setVeiculo(veiculo);
        return pessoasEnvolvidasRepository.save(pessoasEnvolvidasEntity);
    }

    public List<PessoasEnvolvidasEntity> listarPessoas() {
        return pessoasEnvolvidasRepository.findAll();
    }

    public PessoasEnvolvidasEntity listarPessoasPorId(UUID id) {
        return pessoasEnvolvidasRepository.findById(id).orElseThrow(() -> new NotFoundException("Esse ID não corresponde a nenhuma pessoa"));
    }

    public PessoasEnvolvidasEntity atualizarPessoasPorId(UUID id,PessoasEnvolvidasEntity pessoasEnvolvidasEntity) {
        PessoasEnvolvidasEntity pessoaAtualizada = pessoasEnvolvidasRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Esse ID não corresponde a nenhuma pessoa"));
        if(pessoasEnvolvidasEntity.getNome() != null) {
            pessoaAtualizada.setNome(pessoasEnvolvidasEntity.getNome());
        }
        if(pessoasEnvolvidasEntity.getRg() != null) {
            pessoaAtualizada.setRg(pessoasEnvolvidasEntity.getRg());
        }
        if(pessoasEnvolvidasEntity.getCpf() != null) {
            pessoaAtualizada.setCpf(pessoasEnvolvidasEntity.getCpf());
        }
        if(pessoasEnvolvidasEntity.getEndereco() != null) {
            pessoaAtualizada.setEndereco(pessoasEnvolvidasEntity.getEndereco());
        }
        if(pessoasEnvolvidasEntity.getTipo() != null) {
            pessoaAtualizada.setTipo(pessoasEnvolvidasEntity.getTipo());
        }
        validarVeiculo(pessoasEnvolvidasEntity);

        VeiculoEntity veiculo = buscarVeiculoPorId(pessoasEnvolvidasEntity);
        pessoasEnvolvidasEntity.setVeiculo(veiculo);

        return pessoasEnvolvidasRepository.save(pessoaAtualizada);

    }

    // Metodos privados

    private void validarVeiculo(PessoasEnvolvidasEntity pessoasEnvolvidasEntity) {
        if(pessoasEnvolvidasEntity.getVeiculo() == null ||  pessoasEnvolvidasEntity.getVeiculo().getId() == null){
            throw new IllegalArgumentException("É necessario informar um veiculo valido");
        }
    }

    private VeiculoEntity buscarVeiculoPorId(PessoasEnvolvidasEntity pessoasEnvolvidasEntity) {
        return veiculoRepository.findById(pessoasEnvolvidasEntity.getVeiculo().getId())
                .orElseThrow(() -> new NotFoundException("Esse ID não corresponde a nehnum veiculo"));
    }
}
