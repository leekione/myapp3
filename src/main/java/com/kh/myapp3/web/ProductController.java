package com.kh.myapp3.web;

import com.kh.myapp3.domain.Product;
import com.kh.myapp3.domain.svc.ProductSVC;
import com.kh.myapp3.web.form.SaveForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductSVC productSVC;;

    //등록양식
    @GetMapping
    public String saveForm(){

        return "product/saveForm"; //상품등록 view
    }

    //등록처리
    @PostMapping
    public String save( SaveForm saveForm) {
        log.info("saveForm:{}",saveForm);

        Product product = new Product();
        product.setPname(saveForm.getPname());
        product.setQuantity(saveForm.getQuantity());
        product.setPrice(saveForm.getPrice());

        Integer productId = productSVC.save(product);

        return "redirect:/product/"+productId; //상품상세 view
    }

    //상품개별조회
    @GetMapping("/{pid}")
    public String findByProductId(
        @PathVariable("pid") String pid){
        //db에서 상품조회

        return "product/detailForm"; //상품상세 view
    }

    //수정양식
    @GetMapping("/{pid}/edit")
    public String updateForm(){

        return "product/updateForm"; //상품수정 view
    }

    //수정처리
    @PostMapping("/{pid}/edit")
    public String update(){

        return "redirect:/product1"; //상품상세 view
    }

    //삭제처리
    @GetMapping("/{pid}/del")
    public String delete(){

        return "redirect:/product/all"; //전체목록 view
    }

    //목록
    @GetMapping("all")

    public String list(){
        return "/product/all";
    }
}
