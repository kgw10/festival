package com.festival.Repository;

import com.festival.Entity.Festival;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FestivalRepository extends JpaRepository<Festival, Long> {
    List<Festival> findByEventstartdateLessThanEqualAndEventenddateGreaterThanEqualOrderByEventenddate(Integer startDate, Integer endDate);
    List<Festival> findByTitleContainingOrAddr1Containing(String title, String addr1);
    List<Festival> findAll();
}
