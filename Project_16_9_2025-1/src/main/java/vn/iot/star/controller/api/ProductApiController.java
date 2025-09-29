package vn.iot.star.controller.api;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.iot.star.entity.Category;
import vn.iot.star.entity.Product;
import vn.iot.star.model.ProductModel;
import vn.iot.star.model.Response;
import vn.iot.star.service.CategoryService;
import vn.iot.star.service.IProductService;
import vn.iot.star.service.IStorageService;

@RestController
@RequestMapping("/api/product")
public class ProductApiController {

    @Autowired
    private IProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private IStorageService storageService;

    // Lấy tất cả sản phẩm
    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        return ResponseEntity.ok(new Response(true, "Thành công", productService.findAll()));
    }

    // Thêm sản phẩm mới
    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@ModelAttribute ProductModel productModel) {
        Optional<Product> optProduct = productService.findByProductName(productModel.getProductName());

        if (optProduct.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Response(false, "Sản phẩm đã tồn tại", optProduct.get()));
        }

        Product product = new Product();
        BeanUtils.copyProperties(productModel, product);

        // Gán Category
        Optional<Category> optCategory = categoryService.findById(productModel.getCategoryId());
        optCategory.ifPresent(product::setCategory);

        // Xử lý lưu file ảnh
        if (productModel.getImageFile() != null && !productModel.getImageFile().isEmpty()) {
            String uuid = UUID.randomUUID().toString();
            String fileName = storageService.getSorageFilename(productModel.getImageFile(), uuid);
            product.setImages(fileName);
            storageService.store(productModel.getImageFile(), fileName);
        }

        product.setCreateDate(new Date());
        productService.save(product);

        return ResponseEntity.ok(new Response(true, "Thêm sản phẩm thành công", product));
    }
}
