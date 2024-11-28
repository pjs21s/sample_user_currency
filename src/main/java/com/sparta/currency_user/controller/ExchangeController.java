package com.sparta.currency_user.controller;

import com.sparta.currency_user.dto.ExchangeRequestDto;
import com.sparta.currency_user.dto.ExchangeResponseDto;
import com.sparta.currency_user.service.ExchangeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/exchange")
public class ExchangeController {
    private final ExchangeService exchangeService;

    public ExchangeController(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    //Create
    @PostMapping
    public ResponseEntity<ExchangeResponseDto> createExchange(@RequestBody ExchangeRequestDto exchangeRequestDto){
        return ResponseEntity.ok().body(exchangeService.save(exchangeRequestDto));
    }

    //Read
    @GetMapping("/{userId}")
    public ResponseEntity<List<ExchangeResponseDto>> getExchangeByUserId(@PathVariable Long userId){
        return ResponseEntity.ok().body(exchangeService.getExchangeById(userId));
    }

    //Update
    @PatchMapping("/{id}")
    public ResponseEntity<ExchangeResponseDto> updateExchange(@PathVariable Long id,
                                                              @RequestBody ExchangeRequestDto exchangeRequestDto){

        ExchangeResponseDto responseDto = exchangeService.update(exchangeRequestDto,id);
        return ResponseEntity.ok().body(responseDto);

    }

    //delete



}
