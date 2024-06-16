package com.softlottery.lottery.lottery.controller;

import com.softlottery.lottery.lottery.dto.LotteryRequestDto;
import com.softlottery.lottery.lottery.dto.LotteryResponseDto;
import com.softlottery.lottery.lottery.mapper.LotteryRestMapper;
import com.softlottery.lottery.lottery.service.CreateLotteryService;
import com.softlottery.lottery.lottery.service.FindLotteryService;
import com.softlottery.lottery.lottery.service.UpdateLotteryService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/v1/lottery")
public class LotteryController {
    private final CreateLotteryService createLotteryService;
    private final UpdateLotteryService updateLotteryService;
    private final FindLotteryService findLotteryService;
    private final LotteryRestMapper lotteryRestMapper;
    LotteryController(
            CreateLotteryService createLotteryService,
            UpdateLotteryService updateLotteryService,
            FindLotteryService findLotteryService,
            LotteryRestMapper lotteryRestMapper
    ){
        this.createLotteryService = createLotteryService;
        this.updateLotteryService = updateLotteryService;
        this.findLotteryService = findLotteryService;
        this.lotteryRestMapper = lotteryRestMapper;
    }
    @GetMapping("/pageable/{idUser}/{idRaffle}")
    public ResponseEntity<Page<LotteryResponseDto>> listPaginate(@PageableDefault Pageable pageable, @PathVariable("idUser") Long idUser,@PathVariable("idRaffle") Long idRaffle) {
        var response = this.findLotteryService.pageableAllLottery(pageable,idUser,idRaffle,"");
        var responseDtoList = response.getContent().stream()
                .map(this.lotteryRestMapper::toResponse)
                .toList();
        var responseDtoPage = new PageImpl<>(responseDtoList, pageable,
                response.getTotalElements());
        return ResponseEntity.ok(responseDtoPage);

    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid LotteryRequestDto lotteryRequestDto, HttpServletResponse response, UriComponentsBuilder uriBuilder) {
        var id = this.createLotteryService.create(this.lotteryRestMapper.toDomain(lotteryRequestDto));
        var uri = uriBuilder.path("/api/v1/lottery/{id}")
                .buildAndExpand(id)
                .toUri();
        response.setHeader(HttpHeaders.LOCATION, uri.toString());
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Valid LotteryRequestDto lotteryRequestDto, @PathVariable("id") Long id) {
        this.updateLotteryService.update(this.lotteryRestMapper.toDomain(lotteryRequestDto),id);
    }

    @GetMapping("/findAll/{idUser}/{idRaffle}")
    public ResponseEntity<List<LotteryResponseDto>> findByAll(@PathVariable("idUser") Long idUser,@PathVariable("idRaffle") Long idRaffle){
        var responseList = this.findLotteryService.findAllList(idUser,idRaffle);
        return ResponseEntity.ok(responseList);
    }
    @GetMapping("/{id}")
    public ResponseEntity<LotteryResponseDto> findById(@PathVariable("id") Long id)  {
        var Response = this.lotteryRestMapper.toResponse(this.findLotteryService.findById(id).get());
        return ResponseEntity.ok(Response);
    }

}
