package com.kh.myapp3.web.admin.form.member;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDateTime;

@Data
public class MemberForm {
    @NumberFormat(pattern = "0.##")
    private Long memberId;
    private String email;
    private String pw;      //비밀번호
    private String nickname;//닉네임

    @DateTimeFormat(pattern = "yy-MM-dd a HH:mm:ss")  //@Number
    private LocalDateTime cdate;//가입일
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime udate;//수정일
}
