package com.sparta.currency_user.controller;

import com.sparta.currency_user.dto.ExchangeClaimRequestDto;
import com.sparta.currency_user.dto.ExchangeClaimResponseDto;
import com.sparta.currency_user.service.ExchangeClaimService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exchanges")
public class ExchangeClaimController {

    public final ExchangeClaimService exchangeClaimService;

    @PostMapping
    public ResponseEntity<ExchangeClaimResponseDto> createExchangeRequest(@RequestBody @Valid ExchangeClaimRequestDto exchangeRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(exchangeClaimService.save(exchangeRequestDto));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<ExchangeClaimResponseDto>> getExchangeClaimsByUserId(@PathVariable("userId") Long userId){
        return ResponseEntity.ok(exchangeClaimService.getExchangeClaimsByUserId(userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> cancelExchangeClaims(@PathVariable("id") Long id){
        return ResponseEntity.ok(exchangeClaimService.cancelExchangeClaim(id));
    }

}
