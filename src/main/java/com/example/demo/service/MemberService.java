package com.example.demo.service;

import com.example.demo.entity.dto.MemberDetailDTO;
import com.example.demo.entity.dto.MemberLoginDTO;
import com.example.demo.entity.dto.MemberSaveDTO;

import java.util.List;

public interface MemberService {
    Long save(MemberSaveDTO memberSaveDTO);

    boolean login(MemberLoginDTO memberLoginDTO);

    List<MemberDetailDTO> findAll();

    MemberDetailDTO findById(Long memberId);

    void deleteById(Long memberId);
    Long update(MemberDetailDTO memberDetailDTO);
}

