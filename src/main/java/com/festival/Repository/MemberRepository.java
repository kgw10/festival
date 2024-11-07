package com.festival.Repository;

import com.festival.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsById(String id);  // 사용자 ID는 String으로 중복 체크
    boolean existsByNickname(String nickname);
    Member findById(String id);  // id를 String 타입으로 검색
    Member findByNameAndEmail(String name, String email);
    Member findByIdAndNameAndEmail(String id, String name, String email);
}