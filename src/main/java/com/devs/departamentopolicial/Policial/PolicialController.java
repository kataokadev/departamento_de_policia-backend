package com.devs.departamentopolicial.Policial;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/policiais")
public class PolicialController {

    private final PolicialService policialService;

    public PolicialController(PolicialService policialService) {
        this.policialService = policialService;
    }

    @PostMapping
    public ResponseEntity<PolicialEntity> addPolicial(@RequestBody PolicialEntity policialEntity) {
        PolicialEntity policial = policialService.addPolicial(policialEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(policial);
    }

    @GetMapping
    public ResponseEntity<List<PolicialEntity>> getPolicials() {
        List<PolicialEntity> listaPoliciais = policialService.buscarPoliciais();
        return ResponseEntity.status(HttpStatus.OK).body(listaPoliciais);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PolicialEntity> buscarPolicaisPorId(@PathVariable UUID id) {
        PolicialEntity policialId = policialService.buscarPoliciaisPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(policialId);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PolicialEntity> atualizarPolicialPorId(@PathVariable UUID id, @RequestBody PolicialEntity policialEntity) {
        PolicialEntity policialAtualizado = policialService.atualizarPoliciaisPorId(id, policialEntity);
        return ResponseEntity.status(HttpStatus.OK).body(policialAtualizado);
    }
}
