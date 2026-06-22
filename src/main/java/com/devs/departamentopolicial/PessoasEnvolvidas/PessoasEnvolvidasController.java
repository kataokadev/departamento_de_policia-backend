package com.devs.departamentopolicial.PessoasEnvolvidas;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pessoasenvolvidas")
public class PessoasEnvolvidasController {

    private final PessoasEnvolvidasService pessoasEnvolvidasService;

    public PessoasEnvolvidasController(PessoasEnvolvidasService pessoasEnvolvidasService) {
        this.pessoasEnvolvidasService = pessoasEnvolvidasService;
    }

    @PostMapping
    public ResponseEntity<PessoasEnvolvidasEntity> adicionarPessoas(@RequestBody PessoasEnvolvidasEntity pessoaEntity){
        PessoasEnvolvidasEntity pessoas = pessoasEnvolvidasService.adicionarPessoas(pessoaEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoas);
    }

    @GetMapping
    public ResponseEntity<List<PessoasEnvolvidasEntity>> listarPessoas(){
        List<PessoasEnvolvidasEntity> pessoas = pessoasEnvolvidasService.listarPessoas();
        return  ResponseEntity.status(HttpStatus.OK).body(pessoas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoasEnvolvidasEntity>  listarPessoasPorId(@PathVariable UUID id){
        PessoasEnvolvidasEntity pessoas =  pessoasEnvolvidasService.listarPessoasPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(pessoas);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PessoasEnvolvidasEntity> atualizarPessoas(@PathVariable UUID id, @RequestBody PessoasEnvolvidasEntity pessoaEntity){
        PessoasEnvolvidasEntity pessoas = pessoasEnvolvidasService.atualizarPessoasPorId(id, pessoaEntity);
        return ResponseEntity.status(HttpStatus.OK).body(pessoas);
    }
}
