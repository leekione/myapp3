package com.kh.myapp3.domain.admin;

import com.kh.myapp3.domain.dao.Member;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;

import java.util.List;

@Slf4j
@SpringBootTest
class AdminMemberDAOImplTest {

    @Autowired
    private AdminMemberDAO adminMemberDAO;

    @Test
    @DisplayName("회원가입")
    //@Transactional // 테스트 환경에서는 메소드 종료전 롤백시킴
    //@Disabled //테스트 대상에서 제외
    void insert() {
        Member member = new Member();
        member.setMemberId(2l);
        member.setNickname("홍길동");
        member.setEmail("test13@test.com");
        member.setPw("1234");
        int affectedRow = adminMemberDAO.insert(member);

        log.info("affectedRow={}",affectedRow);
        Assertions.assertThat(affectedRow).isEqualTo(1);

    }
    @Test
    @DisplayName("회원아이디 중복")
    void insertThrow(){
        Member member = new Member();
        member.setMemberId(2l);
        member.setNickname("홍길동");
        member.setEmail("test13@test.com");
        member.setPw("1234");

        org.junit.jupiter.api.Assertions.assertThrows(
                DuplicateKeyException.class,
                ()->adminMemberDAO.insert(member)
        );
    }



    @Test
    @DisplayName("회원조회-회원존재하는 경우")
    void findById(){
        Member findMember = adminMemberDAO.findById(1l);
        log.info("findMember={}",findMember);
        Assertions.assertThat(findMember.getNickname()).isEqualTo("홍길동");
        Assertions.assertThat(findMember.getEmail()).isEqualTo("test12@test.com");
    }

    @Test
    @DisplayName("회원조회-회원존재하지 않는 경우")
    void findByIdWhenIsNotExitMember(){
        Member member = adminMemberDAO.findById(123l);
        Assertions.assertThat(member).isNull();
    }

    @Test
    @DisplayName("회원목록")
    void All(){
        List<Member> list = adminMemberDAO.all();
        log.info("회원수={}",list.size());
//        for (Member member : list) {
//            log.info(member.toString());
//        }
        list.stream().forEach(member->log.info(member.toString()));
    }

    @Test
    @DisplayName("회원수정")
    void update(){
        Long memberId = 2l;
        Member member = new Member();
        member.setNickname("홍길동10");
        member.setPw("09876");
        int affectedRow = adminMemberDAO.update(memberId, member);

        Member updateMember = adminMemberDAO.findById(memberId);

        Assertions.assertThat(updateMember.getNickname()).isEqualTo("홍길동10");
        Assertions.assertThat(updateMember.getPw()).isEqualTo("09876");

    }
}