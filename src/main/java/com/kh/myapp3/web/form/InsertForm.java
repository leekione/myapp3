package com.kh.myapp3.web.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class InsertForm {
    private String email;
    private String pw;
    private String nickname;
}
