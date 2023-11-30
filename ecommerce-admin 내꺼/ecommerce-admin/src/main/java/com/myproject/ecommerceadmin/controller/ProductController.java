package com.myproject.ecommerceadmin.controller;

import com.myproject.ecommerceadmin.annotation.AuthUser;
import com.myproject.ecommerceadmin.domain.product.Product;
import com.myproject.ecommerceadmin.domain.user.AdminUser;
import com.myproject.ecommerceadmin.dto.product.ProductDTO;
import com.myproject.ecommerceadmin.dto.product.ProductRegistrationFormDTO;
import com.myproject.ecommerceadmin.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping(value = "/product/registration")
    public String registrationForm(){
        log.info(">>> 상품 등록 폼");
        return "/product/registration";
    }

    @PostMapping(value = "/product/insert")
    public String insert (@AuthUser AdminUser adminUser, @RequestParam(name = "image") MultipartFile firstImage, @RequestParam(name = "images") MultipartFile[] images, ProductRegistrationFormDTO productRegistrationFormDTO) throws Exception{
        log.info(">>> 상품 등록 진행, {}", productRegistrationFormDTO);
        log.info(">>> 상품 등록 완료, {}", productService.insert(adminUser, firstImage, images, productRegistrationFormDTO));

        return "redirect:/product/list";
    }

    @GetMapping("/product/list")
    public String list(Model model){
        List<ProductDTO> productDTOList = productService.list();
        model.addAttribute(productDTOList);
        log.info(">>> 상품 목록 조회, {}", productDTOList);
        return "/product/list";
    }

    @GetMapping("/product/one/productId={productId}")
    public  String one(@PathVariable Long productId, Model model) throws Exception{
        model.addAttribute("productDTO", productService.one(productId));
        return "/product/one";
    }
}
