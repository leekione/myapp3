package com.kh.myapp3.domain.admin;

import com.kh.myapp3.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminMemberSVCImpl implements AdminMemberSVC {

    private final AdminMemberDAO adminMemberDAO;
    /**
     * 등록
     *
     * @param member 회원정보
     * @return 회원아이디
     */
    @Override
    public Member insert(Member member) {

        //회원아이디 생성
        Long generateMemberId = adminMemberDAO.generateMemberId();
        member.setMemberId(generateMemberId);
        adminMemberDAO.insert(member);
        return adminMemberDAO.findById(generateMemberId);//dao와 svc 타입이 다름
    }

    /**
     * 조회 by 회원 아이디
     *
     * @param memberId 회원아이디
     * @return 회원정보
     */
    @Override
    public Member findById(Long memberId) {
        return adminMemberDAO.findById(memberId);
    }

    /**
     * 수정
     *
     * @param memberId 아이디
     * @param member   수정할 회원 정보
     * @return 수정건수
     */
    @Override
    public int update(Long memberId, Member member) {
        int cnt = adminMemberDAO.update(memberId,member);
        log.info("수정건수={}",cnt);
        return cnt;
    }

    /**
     * 탈퇴
     *
     * @param memberId 회원아이디
     * @return 삭제건수
     */
    @Override
    public int delete(Long memberId) {
        int cnt = adminMemberDAO.delete(memberId);
        log.info("삭제건수={}", cnt);
        return cnt;
    }

    /**
     * 전체목록
     *
     * @return 회원전체
     */
    @Override
    public List<Member> all() {
        return adminMemberDAO.all();
    }
}
