package com.kh.myapp3.web;

import com.kh.myapp3.domain.Member;
import com.kh.myapp3.domain.svc.MemberSVC;
import com.kh.myapp3.web.form.InsertForm;
import com.kh.myapp3.web.form.MemEditForm;
import com.kh.myapp3.web.form.MemForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController_old {

    private final MemberSVC memberSVC;

    //등록양식
    @GetMapping("/save")
    public String insertForm(){

        return "member/saveForm";
    }

    //등록처리
    @PostMapping("/save")
    public String insert(InsertForm insertForm){

        Member member = new Member();
        member.setEmail(insertForm.getEmail());
        member.setPw(insertForm.getPw());
        member.setNickname(insertForm.getNickname());

        Member insertedMember = memberSVC.insert(member);

        return "redirect:/members/"+insertedMember.getMemberId();
    }

    //개별 조회
    @GetMapping("/{mid}")
    public String findByMemberId(
        @PathVariable("mid") Long mid,
        Model model
    ){
        Member findedMember = memberSVC.findById(mid);

        MemForm memForm = new MemForm();
        memForm.setMemberId(findedMember.getMemberId());
        memForm.setEmail(findedMember.getEmail());
        memForm.setPw(findedMember.getPw());
        memForm.setNickname(findedMember.getNickname());

        model.addAttribute("memForm",memForm);

        return "member/memForm";

    }

    //수정양식
    @GetMapping("/{mid}/edit")
    public String memUpdateForm(@PathVariable("mid") Long mid, Model model){

        Member findedMember = memberSVC.findById(mid);

        MemEditForm memEditForm = new MemEditForm();
        memEditForm.setMemberId(findedMember.getMemberId());
        memEditForm.setEmail(findedMember.getEmail());
        memEditForm.setPw(findedMember.getPw());
        memEditForm.setNickname(findedMember.getNickname());

        model.addAttribute("memEditForm",memEditForm);

        return "member/editForm";
    }



    //수정처리
    @PostMapping("/{mid}/edit")
    public String memUpdate(@PathVariable("mid") Long mid, MemEditForm memEditForm){
        Member member = new Member();
        member.setMemberId(mid);
        member.setEmail(memEditForm.getEmail());
        member.setPw(memEditForm.getPw());
        member.setNickname(memEditForm.getNickname());

        memberSVC.update(mid,member);
        return "redirect:/members/"+mid;
    }

    //탈퇴처리
    @GetMapping("/{mid}/del")
    public String delete(@PathVariable("mid") Long mid){
        memberSVC.delete(mid);
        return "redirect:/members";
    }

    //전체목록
    @GetMapping
    public String list(Model model) {
        List<Member> list = memberSVC.all();
        model.addAttribute("list",list);
        return "member/all";

    }

}
