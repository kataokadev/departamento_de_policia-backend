package com.devs.departamentopolicial.Relatorios;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/relatorio")
public class RelatorioController {

    private final RelatorioService relatorioService;

    public RelatorioController(RelatorioService relatorioService) {
        this.relatorioService = relatorioService;
    }

    @PostMapping
    public ResponseEntity<RelatorioEntity> adicionarRelatorio(@RequestBody RelatorioEntity relatorioEntity) {
        RelatorioEntity relatorio = relatorioService.salvarRelatorio(relatorioEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(relatorio);
    }

    @GetMapping
    public ResponseEntity<List<RelatorioEntity>> buscarRelatorios() {
        List<RelatorioEntity> relatorios = relatorioService.listarRelatorios();
        return ResponseEntity.status(HttpStatus.OK).body(relatorios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RelatorioEntity> buscarRelatorioPorId(@PathVariable UUID id) {
        RelatorioEntity relatorio = relatorioService.listarRelatorioPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(relatorio);
    }

    @PatchMapping
    public ResponseEntity<RelatorioEntity> atualizarRelatorioPorId(UUID id,@RequestBody RelatorioEntity relatorioEntity) {
        RelatorioEntity relatorio = relatorioService.atualizarRelatorioPorId(id, relatorioEntity);
        return ResponseEntity.status(HttpStatus.OK).body(relatorio);
    }
}
