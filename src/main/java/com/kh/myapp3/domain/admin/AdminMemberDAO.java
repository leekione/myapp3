package com.kh.myapp3.domain.admin;

import com.kh.myapp3.domain.dao.Member;

import java.util.List;

public interface AdminMemberDAO {

    /**
     * 전체목록
     * @return 회원전체
     */
    List<Member> all();

    /**
     * 등록
     * @param member 회원정보
     * @return 가입건수
     */
    int insert(Member member);

    /**
     * 조회 by 회원 아이디
     * @param memberId 회원아이디
     * @return 회원정보
     */
    Member findById(Long memberId);

    /**
     * 수정
     * @param memberId 아이디
     * @param member 수정할 회원 정보
     * @return 수정건수
     */
    int update(Long memberId, Member member);

    /**
     * 탈퇴
     * @param memberId 회원아이디
     * @return 삭제건수
     */
    int delete(Long memberId);


    /**
     * 신규 회원아이디(내부관리용) 생성
     * @return 회원아이디
     */
    Long generateMemberId();


    /**
     * 이메일 중복체크
     * @param email 이메일
     * @return 존재하면 true
     */
    Boolean dupChkOfMemberEmail(String email);
}
