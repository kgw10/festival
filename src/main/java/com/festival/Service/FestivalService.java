package com.festival.Service;

import com.festival.DTO.FestivalDTO;
import com.festival.Entity.Festival;
import com.festival.Repository.FestivalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
//@RequiredArgsConstructor
public class FestivalService {
    private final FestivalRepository festivalRepository;
    public FestivalService(FestivalRepository festivalRepository){
        this.festivalRepository=festivalRepository;
    }
    public List<FestivalDTO> getFestivalsByDate(LocalDate date) {
        Integer formatDate=Integer.parseInt(date.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        List<Festival> festivals=festivalRepository.findByEventstartdateLessThanEqualAndEventenddateGreaterThanEqualOrderByEventenddate(formatDate, formatDate);
        return festivals.stream().map(FestivalDTO::new).collect(Collectors.toList());
    }
    public List<FestivalDTO> searchFestivals(String searchTerm) {
        return festivalRepository.findByTitleContainingOrAddr1Containing(searchTerm, searchTerm)
                .stream().map(FestivalDTO::new).collect(Collectors.toList());
    }
    public List<FestivalDTO> fetchFestivals() {
        List<Festival> festivals=festivalRepository.findAll();
        return festivals.stream().map(FestivalDTO::new).collect(Collectors.toList());
    }
    public FestivalDTO festivalInfo(Long id) {
        Festival festival=festivalRepository.findById(id).orElseThrow(()->new RuntimeException("축제 정보가 없습니다"));
        return new FestivalDTO(festival);
    }
}
