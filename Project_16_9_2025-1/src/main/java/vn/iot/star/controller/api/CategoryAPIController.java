package vn.iot.star.controller.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import vn.iot.star.entity.Category;
import vn.iot.star.model.Response;
import vn.iot.star.service.CategoryService;

@RestController
@RequestMapping(path = "/api/category")
public class CategoryAPIController {

    @Autowired
    private CategoryService categoryService;

    // 1. Lấy tất cả Category
    @GetMapping
    public ResponseEntity<?> getAllCategory() {
        return ResponseEntity.ok(
            new Response(true, "Thành công", categoryService.findAll())
        );
    }

    // 2. Lấy Category theo ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getCategory(@PathVariable("id") Integer id) {
        Optional<Category> category = categoryService.findById(id);

        if (category.isPresent()) {
            return ResponseEntity.ok(
                new Response(true, "Thành công", category.get())
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new Response(false, "Không tìm thấy Category", null)
            );
        }
    }

    // 3. Thêm mới Category
    @PostMapping
    public ResponseEntity<?> addCategory(
            @Validated @RequestParam("name") String name,
            @Validated @RequestParam("description") String description) {

        Optional<Category> optCategory = categoryService.findByName(name);

        if (optCategory.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new Response(false, "Category đã tồn tại trong hệ thống", null)
            );
        } else {
            Category category = new Category();
            category.setName(name);
            category.setDescription(description);

            categoryService.save(category);

            return ResponseEntity.status(HttpStatus.CREATED).body(
                new Response(true, "Thêm Thành công", category)
            );
        }
    }

    // 4. Cập nhật Category
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(
            @PathVariable("id") Integer id,
            @Validated @RequestParam("name") String name,
            @Validated @RequestParam("description") String description) {

        Optional<Category> optCategory = categoryService.findById(id);

        if (optCategory.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new Response(false, "Không tìm thấy Category", null)
            );
        } else {
            Category category = optCategory.get();
            category.setName(name);
            category.setDescription(description);

            categoryService.save(category);

            return ResponseEntity.ok(
                new Response(true, "Cập nhật Thành công", category)
            );
        }
    }

    // 5. Xóa Category
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") Integer id) {
        Optional<Category> optCategory = categoryService.findById(id);

        if (optCategory.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new Response(false, "Không tìm thấy Category", null)
            );
        } else {
            categoryService.delete(optCategory.get());

            return ResponseEntity.ok(
                new Response(true, "Xóa Thành công", optCategory.get())
            );
        }
    }
}
