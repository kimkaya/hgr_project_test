package com.example.demo.repository;

import com.example.demo.entity.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    MemberEntity findByMemberEmail(String memberEmail);
}