package com.softlottery.lottery.general.gameraffle.controller;

import com.softlottery.lottery.general.gameraffle.dto.RaffleRequestDto;
import com.softlottery.lottery.general.gameraffle.dto.RaffleResponseDto;
import com.softlottery.lottery.general.gameraffle.mapper.RaffleRestMapper;
import com.softlottery.lottery.general.gameraffle.service.CreateRaffleService;
import com.softlottery.lottery.general.gameraffle.service.FindRaffleService;
import com.softlottery.lottery.general.gameraffle.service.UpdateRaffleService;
import com.softlottery.lottery.lottery.dto.LotteryRequestDto;
import com.softlottery.lottery.lottery.dto.LotteryResponseDto;
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
@RequestMapping("/api/v1/raffle")
public class RaffleController {

    private final CreateRaffleService createRaffleService;

    private final UpdateRaffleService updateRaffleService;

    private final FindRaffleService findRaffleService;

    private final RaffleRestMapper mapper;


    RaffleController(
            CreateRaffleService createRaffleService,
            UpdateRaffleService updateRaffleService,
            FindRaffleService findRaffleService,
            RaffleRestMapper mapper
    ){
        this.createRaffleService = createRaffleService;
        this.updateRaffleService = updateRaffleService;
        this.findRaffleService = findRaffleService;
        this.mapper = mapper;
    }
    @GetMapping("/pageable")
    public ResponseEntity<Page<RaffleResponseDto>> listPaginate(@PageableDefault Pageable pageable) {
        var response = this.findRaffleService.pageableAllLottery(pageable,"");
        var responseDtoList = response.getContent().stream()
                .map(this.mapper::toResponse)
                .toList();
        var responseDtoPage = new PageImpl<>(responseDtoList, pageable,
                response.getTotalElements());
        return ResponseEntity.ok(responseDtoPage);

    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid RaffleRequestDto raffleRequestDto, HttpServletResponse response, UriComponentsBuilder uriBuilder) {
        var id = this.createRaffleService.create(this.mapper.toDomain(raffleRequestDto));
        var uri = uriBuilder.path("/api/v1/raffle/{id}")
                .buildAndExpand(id)
                .toUri();
        response.setHeader(HttpHeaders.LOCATION, uri.toString());
    }
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Valid RaffleRequestDto raffleRequestDto, @PathVariable("id") Long id) {
        this.updateRaffleService.update(this.mapper.toDomain(raffleRequestDto),id);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<RaffleResponseDto>> findByAll(){
        var responseList = this.findRaffleService.findAllList();
        var response = responseList.stream()
                .map(this.mapper::toResponse)
                .toList();
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<RaffleResponseDto> findById(@PathVariable("id") Long id)  {
        var Response = this.mapper.toResponse(this.findRaffleService.findById(id).get());
        return ResponseEntity.ok(Response);
    }

}
