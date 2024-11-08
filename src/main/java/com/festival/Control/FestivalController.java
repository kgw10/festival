package com.festival.Control;

import com.festival.DTO.FestivalDTO;
import com.festival.Service.FestivalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/festival")
@RequiredArgsConstructor
public class FestivalController {
    private final FestivalService festivalService;
    @GetMapping("/")
    public String festival(){
        return "festival/festival";
    }
    @GetMapping("/data")
    @ResponseBody
    public ResponseEntity<List<FestivalDTO>> getFestivalsByDate(@RequestParam("date") LocalDate date){
        List<FestivalDTO> festivals=festivalService.getFestivalsByDate(date);
        return ResponseEntity.ok(festivals);
    }
    @GetMapping("/search")
    public String search(@RequestParam("keyword") String searchTerm, Model model){
        List<FestivalDTO> searchResults=festivalService.searchFestivals(searchTerm);
        model.addAttribute("searchResults",searchResults);
        return "festival/search";
    }
    @GetMapping("info/{id}")
    public String festivalinfo(@PathVariable Long id, Model model){
        FestivalDTO festival=festivalService.festivalInfo(id);
        model.addAttribute("festival",festival);
        return "festival/festivalinfo";
    }
}
