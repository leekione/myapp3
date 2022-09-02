package com.kh.myapp3.domain.svc;

import com.kh.myapp3.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MemberSVCImplTest {

    @Autowired

    private MemberSVC memberSVC;
    private static Member member;

    @Test
    @DisplayName("가입")
    @Order(1)
    void insert() {
        member = new Member("test10@test.com","1234","별칭5");

        Member insertedMember = memberSVC.insert(member);
        log.info("insertedMember={}",insertedMember);
        Assertions.assertThat(insertedMember.getEmail()).isEqualTo(member.getEmail());
        Assertions.assertThat(insertedMember.getPw()).isEqualTo(member.getPw());
        Assertions.assertThat(insertedMember.getNickname()).isEqualTo(member.getNickname());
    }

    @Test
    @DisplayName("조회")
    @Order(2)
    void findById() {
        
        Member findedMember = memberSVC.findById(member.getMemberId());
        Assertions.assertThat(findedMember).isEqualTo(member);
        
    }

    @Test
    @DisplayName("수정")
    @Order(3)
    void update() {
        String pw = "9999";
        String nickname = "별칭9999";
        
        //수정정보
        Member m = new Member();
        m.setPw(pw);
        m.setNickname(nickname);
        //수정
        memberSVC.update(member.getMemberId(),m);
        //조회
        Member findedMember = memberSVC.findById(member.getMemberId());
        //비교
        Assertions.assertThat(findedMember.getPw()).isEqualTo(pw);
        Assertions.assertThat(findedMember.getNickname()).isEqualTo(nickname);

    }

    @Test
    @DisplayName("삭제")
    @Order(5)
    void delete() {
        memberSVC.delete(member.getMemberId());
        Member findedMember = memberSVC.findById(member.getMemberId());
        Assertions.assertThat(findedMember).isNull();
    }

    @Test
    @DisplayName("목록")
    @Order(4)
    void all() {
        List<Member> list = memberSVC.all();
        Member findedMember = memberSVC.findById(member.getMemberId());
        Assertions.assertThat(list).containsAnyOf(findedMember);
    }
}