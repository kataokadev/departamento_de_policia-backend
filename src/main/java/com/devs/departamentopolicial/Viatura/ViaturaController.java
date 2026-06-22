package com.devs.departamentopolicial.Viatura;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/viatura")
public class ViaturaController {

    private final ViaturaService  viaturaService;

    public ViaturaController(ViaturaService viaturaService) {
        this.viaturaService = viaturaService;
    }

    @PostMapping
    public ResponseEntity<ViaturaEntity> adicionarViatura(@RequestBody ViaturaEntity viaturaEntity) {
        ViaturaEntity viatura = viaturaService.adicionarViatura(viaturaEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(viatura);
    }

    @GetMapping
    public ResponseEntity<List<ViaturaEntity>> listarViaturas(@RequestParam(required = false) String modelo) {
        List<ViaturaEntity> viaturas = (modelo != null)
                ? viaturaService.buscarViaturasPorModelo(modelo)
                : viaturaService.buscarViaturas();
        return ResponseEntity.status(HttpStatus.OK).body(viaturas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ViaturaEntity> buscarViaturaPorId(@PathVariable UUID id) {
        ViaturaEntity viatura = viaturaService.buscarViaturaPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(viatura);
    }

    @GetMapping("/placa/{placa}")
    public ResponseEntity<ViaturaEntity> buscarViaturaPorPlaca(@PathVariable String placa) {
        ViaturaEntity viatura = viaturaService.buscarViaturaPorPlaca(placa);
        return ResponseEntity.status(HttpStatus.OK).body(viatura);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ViaturaEntity> atualizarViaturaPorId(@PathVariable UUID id,@RequestBody ViaturaEntity viaturaEntity) {
        ViaturaEntity viatura = viaturaService.atualizarViaturaPorId(id, viaturaEntity);
        return ResponseEntity.status(HttpStatus.OK).body(viatura);
    }

}
