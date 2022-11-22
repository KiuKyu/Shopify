package com.shopify.service.product;

import com.shopify.model.dto.ProductDTO;
import com.shopify.model.persistence.product.Product;
import com.shopify.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository productRepository;

    @Value("${file-upload}")
    private String uploadPath;

    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product testSave(ProductDTO productDTO) {
        MultipartFile img = productDTO.getImage();
        if (img != null && img.getSize() != 0) {
            String fileName = img.getOriginalFilename();
            long currentTime = System.currentTimeMillis();
            fileName = currentTime + "_" + fileName;
            try {
                FileCopyUtils.copy(img.getBytes(), new File(uploadPath + fileName));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Product product = new Product();
            product.setName(productDTO.getName());
            product.setCategory(productDTO.getCategory());
            product.setImage(fileName);
            product.setCreateDate(currentTime);
            productRepository.save(product);
            return product;
        } else {
            return null;
        }
    }

    @Override
    public Product testFind(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (!productOptional.isPresent()) {
            return null;
        } else {
            return productOptional.get();
        }
    }

    @Override
    public Product testUpdate(Long id, ProductDTO productDTO) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (!productOptional.isPresent()) {
            return null;
        } else {
            Product oldProduct = productOptional.get();
            oldProduct.setId(id);
            oldProduct.setName(productDTO.getName());
            oldProduct.setCategory(productDTO.getCategory());
            long currentTime = System.currentTimeMillis();
            oldProduct.setModifyDate(currentTime);

            MultipartFile img = productDTO.getImage();
            if (img != null && img.getSize() != 0) {
                String fileName = img.getOriginalFilename();
                fileName = currentTime + "_" + fileName;
                try {
                    FileCopyUtils.copy(img.getBytes(), new File(uploadPath + fileName));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                oldProduct.setImage(fileName);
            }
            return productRepository.save(oldProduct);
        }
    }
}
