package com.kh.myapp3.web.form.member;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MemberForm {
    private Long memberId;
    private String email;
    private String pw;      //비밀번호
    private String nickname;//닉네임
    private LocalDateTime cdate;//가입일
    private LocalDateTime udate;//수정일
}
