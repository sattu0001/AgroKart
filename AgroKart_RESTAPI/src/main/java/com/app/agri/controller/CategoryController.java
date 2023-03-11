package com.app.agri.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.agri.common.AgriContant;
import com.app.agri.dto.CategoryDto;
import com.app.agri.dto.Response;
import com.app.agri.service.CategoryService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = AgriContant.API_URI+"/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;


	@GetMapping
	public ResponseEntity<?> findAll() {
		return Response.success(categoryService.findAllCategory());
	}

	@GetMapping("/category")
	public ResponseEntity<?> getCategoryById(@RequestParam("categoryId") int id) {
		return Response.success(categoryService.findCategoryById(id));
	}

	@PostMapping("/addcategory")
	public ResponseEntity<?> saveCategory(@Valid @RequestBody CategoryDto c) {
		return Response.success(categoryService.addCategory(c));
	}
	
	@DeleteMapping("/category")
	public ResponseEntity<?> deleteProduct(@RequestParam("categoryId") int categoryId) {
		return Response.success(categoryService.deleteCategory(categoryId));
	}

}
