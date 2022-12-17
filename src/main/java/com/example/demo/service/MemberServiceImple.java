package com.example.demo.service;

import com.example.demo.entity.dto.MemberDetailDTO;
import com.example.demo.entity.dto.MemberLoginDTO;
import com.example.demo.entity.dto.MemberSaveDTO;
import com.example.demo.entity.MemberEntity;
import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImple implements MemberService {

    private final MemberRepository mr;

    @Override
    public Long save(MemberSaveDTO memberSaveDTO) {
        MemberEntity memberEntity = MemberEntity.saveMember(memberSaveDTO);
        return mr.save(memberEntity).getId();
    }

    @Override
    public boolean login(MemberLoginDTO memberLoginDTO) {
        MemberEntity memberEntity = mr.findByMemberEmail(memberLoginDTO.getMemberEmail());
        if (memberEntity!=null) {
            if(memberEntity.getMemberPassword().equals(memberLoginDTO.getMemberPassword())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public List<MemberDetailDTO> findAll() {
        List<MemberEntity> memberEntityList = mr.findAll();
        List<MemberDetailDTO> memberDetailDTOList = MemberDetailDTO.change(memberEntityList);
        return memberDetailDTOList;
    }

    @Override
    public MemberDetailDTO findById(Long memberId) {
        return MemberDetailDTO.toMemberDetailDTO(mr.findById(memberId).get());
    }

    @Override
    public void deleteById(Long memberId) {
        mr.deleteById(memberId);
    }

    @Override
    public Long update(MemberDetailDTO memberDetailDTO) {
        MemberEntity memberEntity = MemberEntity.toUpdateMember(memberDetailDTO);
        Long memberId = mr.save(memberEntity).getId();
        return memberId;
    }
}

