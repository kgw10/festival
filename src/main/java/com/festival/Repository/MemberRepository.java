package com.festival.Repository;

import com.festival.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByUserId(String userId);
    boolean existsByNickname(String nickname);
    Member findByUserId(String userId);
    Member findByNameAndEmail(String name, String email);
    Member findByUserIdAndNameAndEmail(String userId, String name, String email);
}
