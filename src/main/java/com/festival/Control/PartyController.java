package com.festival.Control;

import com.festival.DTO.PartyDTO;
import com.festival.Service.PartyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/party")
@RequiredArgsConstructor
public class PartyController {
    private final PartyService partyService;
//    public PartyController(PartyService partyService){
//        this.partyService=partyService;
//    }
    @GetMapping("/")
    public String party(){
        return "party/party";
    }
    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<String> createParty(@RequestBody @Valid PartyDTO partyDTO){
        partyService.createParty(partyDTO);
        return ResponseEntity.ok("파티 생성 성공!");
    }
}
