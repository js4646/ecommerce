package com.eshop.ecommerce.controller;

import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import com.eshop.ecommerce.model.Category;
import com.eshop.ecommerce.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * CategoryCotroller
 */
@RestController
@RequestMapping("/category")
public class CategoryCotroller {

    @Autowired
    private CategoryService categoryService;
    
    @GetMapping("/")
    public ResponseEntity<List<Category>> getCategory(){
        List<Category> body = categoryService.listCategories();
        return new ResponseEntity<>(body,HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCategory(@Valid @RequestBody Category category){
        if(Objects.nonNull(categoryService.readCategory(category.getCategoryName()))){
            return new ResponseEntity<>(new ApiResponse(false,"category already exists"),HttpStatus.CONFLICT);
        }
        categoryService.createCategory(category);
        return new ResponseEntity<>(new ApiResponse(true,"created the category"),HttpStatus.CREATED);

    }

    @PostMapping("update/{categoryId}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable("categoryId") Integer cotegoryId, @Valid @RequestBody Category category){
        //Check to see if the catogery exists
        if(Objects.nonNull(categoryService.readCategory(cotegoryId))){
            // If category exists then update
            categoryService.updateCategory(cotegoryId, category);
            return new ResponseEntity<>(new ApiResponse(true, "updated category"), HttpStatus.OK);
        }

        return new ResponseEntity<>(new ApiResponse(false,"category doesnt exist"), HttpStatus.NOT_FOUND);
    }
}