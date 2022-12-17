package com.example.demo.entity.dto;

import com.example.demo.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDetailDTO {

    private Long memberId;
    private String memberEmail;
    private String memberPassword;
    private String memberName;

}