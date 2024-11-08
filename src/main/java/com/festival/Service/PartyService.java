package com.festival.Service;

import com.festival.DTO.PartyDTO;
import com.festival.Entity.Party;
import com.festival.Repository.PartyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PartyService {
    private final PartyRepository partyRepository;
//    public PartyService(PartyRepository partyRepository){
//        this.partyRepository=partyRepository;
//    }
    public void createParty(PartyDTO partyDTO){
        Party party=new Party();
        party.setNickname(partyDTO.getNickname());
        party.setFestival_name(partyDTO.getFestival_name());
        party.setBoard_title(partyDTO.getBoard_title());
        party.setParticipant(partyDTO.getParticipant());
        partyRepository.save(party);
    }
}
