package org.example.gateway.controller;

import org.apache.dubbo.config.annotation.DubboReference;
import org.example.common.DTO.RemitDTO;
import org.example.rmrgt.api.IRemitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/remit")
public class RemitController {

    @DubboReference
    private IRemitService iRemitService;

    @PostMapping
    public ResponseEntity<?> transfer(@RequestBody RemitDTO remitDTO) {
        try {
            iRemitService.remitService(remitDTO);
            return ResponseEntity.ok("Transfer successful");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
