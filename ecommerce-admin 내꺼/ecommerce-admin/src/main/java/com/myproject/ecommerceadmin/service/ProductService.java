package com.myproject.ecommerceadmin.service;

import com.myproject.ecommerceadmin.domain.product.Product;
import com.myproject.ecommerceadmin.domain.user.AdminUser;
import com.myproject.ecommerceadmin.dto.product.ProductDTO;
import com.myproject.ecommerceadmin.dto.product.ProductRegistrationFormDTO;
import com.myproject.ecommerceadmin.exception.ProductNotFoundException;
import com.myproject.ecommerceadmin.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    @Value("${image.dir}")
    private String imageDir;
    public Optional<Product> findByIdAndNameContainingAndProductDescContaining(Long id, String name, String productDesc){
        return productRepository.findByIdAndNameContainingAndProductDescContaining(id, name, productDesc);
    }

    public ProductDTO insert(AdminUser adminUser, MultipartFile firstImage, MultipartFile[] images, ProductRegistrationFormDTO productRegistrationFormDTO){
        Product product = productRegistrationFormDTO.toEntity();
        product.setCreatedBy(adminUser.getEmail());
        String uploadFolder = imageDir;
        String folderPath = getFolderPath();
        File uploadPath = new File(uploadFolder, folderPath);
        if(!uploadPath.exists()){
            uploadPath.mkdirs();
        }
        product.setImagePath(folderPath);
        product.setDetailImageCount(images.length);
        Product savedProduct = productRepository.save(product);

        int idx = 0;
        String imageName = Long.toString(savedProduct.getId()) + "_" + Integer.toString(idx);
        String thumbnailName = "s_" + Long.toString(savedProduct.getId());

        try{
            File savedFile = new File(uploadPath, imageName);
            firstImage.transferTo(savedFile);

            File thumbnailFile = new File(uploadPath, thumbnailName);
            Thumbnailator.createThumbnail(savedFile, thumbnailFile,100,100);

        } catch (Exception e){
            e.printStackTrace();
        }
        idx++;
        for(MultipartFile image : images){
            imageName = Long.toString(savedProduct.getId()) + "_" + Integer.toString(idx);
            try{
                File savedFile = new File(uploadPath, imageName);
                image.transferTo(savedFile);
            } catch (Exception e){
                e.printStackTrace();
            }
            idx++;
        }
        return ProductDTO.of(savedProduct);
    }

    private String getFolderPath(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String str = sdf.format(date);
        return str.replace("-", File.separator);
    }
    public List<ProductDTO> list(){
        List<Product> productList = productRepository.findAll();
        List<ProductDTO> productDTOList = new ArrayList<>();
        for(Product product : productList){
            productDTOList.add(addImageUrl(product));
        }
        return productDTOList;
    }

    public ProductDTO one(Long productId) throws Exception{
        Product product = productRepository.findById(productId).orElseThrow();

        return addImageUrl(product);
    }

    private ProductDTO addImageUrl(Product product){
        ProductDTO productDTO = ProductDTO.of(product);
        productDTO.setThumbnailUrl("/image/"+ productDTO.getImagePath() + "/s_" + productDTO.getId() + ".png");
        List<String> imageUrlList = new ArrayList<>();
        for(int i=0; i<=productDTO.getDetailImageCount(); i++){
            imageUrlList.add("/image/"+ productDTO.getImagePath() + "/" + productDTO.getId() + "_" + i);
        }
        productDTO.setImageUrlList(imageUrlList);
        return productDTO;
    }

}
