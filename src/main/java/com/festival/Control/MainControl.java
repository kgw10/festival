package com.festival.Control;

import com.festival.DTO.FestivalDTO;
import com.festival.Service.FestivalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainControl {

    private final FestivalService festivalService;

    @GetMapping("/")
    public String home(Model model) {
        List<FestivalDTO> festivals = festivalService.fetchFestivals();
        model.addAttribute("festivals", festivals);
        return "index";
    }

    @GetMapping("/festival")
    public String festival() {
        return "festival/festival";
    }

    @GetMapping("/party")
    public String party() {
        return "party/board";
    }

    @GetMapping("/diary")
    public String diary() {
        return "diary/diary";
    }
}
