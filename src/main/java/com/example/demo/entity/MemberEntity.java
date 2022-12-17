package com.example.demo.entity;

import com.example.demo.entity.dto.MemberDetailDTO;
import com.example.demo.entity.dto.MemberSaveDTO;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="member_table")
public class MemberEntity {

    @Id // PK로 아래 컬럼을 사용하겠다는 뜻
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(length = 50, unique = true)
    private String memberEmail;

    @Column(length = 20)
    private String memberPassword;

    @Column()
    private String memberName;


    public static MemberEntity saveMember(MemberSaveDTO memberSaveDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberEmail(memberSaveDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberSaveDTO.getMemberPassword());
        memberEntity.setMemberName(memberSaveDTO.getMemberName());

        return memberEntity;
    }

    public static MemberDetailDTO toMemberDetailDTO(MemberEntity memberEntity) {
        MemberDetailDTO memberDetailDTO = new MemberDetailDTO();
        memberDetailDTO.setMemberId(memberEntity.getId());
        memberDetailDTO.setMemberEmail(memberEntity.getMemberEmail());
        memberDetailDTO.setMemberPassword(memberEntity.getMemberPassword());
        memberDetailDTO.setMemberName(memberEntity.getMemberName());
        return memberDetailDTO;
    }

    public static List<MemberDetailDTO> change(List<MemberEntity> memberEntityList) {
        List<MemberDetailDTO> memberDetailDTOList = new ArrayList<>();
        for (MemberEntity m: memberEntityList) {
            memberDetailDTOList.add(toMemberDetailDTO(m));
        }
        return memberDetailDTOList;
    }
}
