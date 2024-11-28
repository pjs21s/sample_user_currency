package com.sparta.record.controller;

import com.sparta.record.dto.find_amount.FindAmountResponseDto;
import com.sparta.record.dto.find_amount.FindAmountServiceDto;
import com.sparta.record.dto.find_currency_data.FindCurrencyDataResponseDto;
import com.sparta.record.dto.find_currency_data.FindCurrencyDataServiceDto;
import com.sparta.record.entity.ExchangeRecord;
import com.sparta.record.repository.ExchangeRecordRepository;
import com.sparta.record.service.ExchangeRecordService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/currency/api/records")
@RequiredArgsConstructor
public class ExchangeRecordController {
    private final ExchangeRecordService exchangeRecordService;

    @GetMapping("/{userId}")
    public ResponseEntity<FindAmountResponseDto> getExchangeRecord(@PathVariable Long userId
            , HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (!Objects.equals((Long) session.getAttribute("userId"), userId)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return ResponseEntity.ok(exchangeRecordService.findAmount(new FindAmountServiceDto(userId)));
    }

    @GetMapping("")
    public ResponseEntity<FindCurrencyDataResponseDto> getExchangeRecord(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int pageSize) {

        return ResponseEntity.ok(exchangeRecordService.findCurrencyData(new FindCurrencyDataServiceDto(page-1,pageSize)));
    }
}