package com.devs.departamentopolicial.Veiculo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/veiculo")
public class VeiculoController {

    private final VeiculoService veiculoService;

    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }

    @PostMapping
    public ResponseEntity<VeiculoEntity> adicionarVeiculo(@RequestBody VeiculoEntity veiculoEntity) {
        VeiculoEntity veiculo = veiculoService.adicionarVeiculo(veiculoEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(veiculo);
    }

    @GetMapping
    public ResponseEntity<List<VeiculoEntity>> listarVeiculos() {
        List<VeiculoEntity> veiculos = veiculoService.listarVeiculos();
        return ResponseEntity.status(HttpStatus.OK).body(veiculos);
    }

    @GetMapping("{id}")
    public ResponseEntity<VeiculoEntity> listarVeiculoPorId(@PathVariable UUID id) {
        VeiculoEntity veiculo = veiculoService.listarVeiculoPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(veiculo);
    }

    @GetMapping("/placa/{placa}")
    public ResponseEntity<VeiculoEntity> listarVeiculoPorPlaca(@PathVariable String placa) {
        VeiculoEntity veiculo = veiculoService.listarVeiculoPorPlaca(placa);
        return ResponseEntity.status(HttpStatus.OK).body(veiculo);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<VeiculoEntity> atualizarVeiculoPorId(@PathVariable UUID id, @RequestBody VeiculoEntity veiculoEntity) {
        VeiculoEntity veiculo = veiculoService.atualizarVeiculoPorId(id, veiculoEntity);
        return ResponseEntity.status(HttpStatus.OK).body(veiculo);
    }
}
