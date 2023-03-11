package com.app.agri.service;

import java.util.List;

import javax.validation.Valid;

import com.app.agri.dto.CategoryDto;
import com.app.agri.dto.ProductDto;
import com.app.agri.entity.Category;


public interface CategoryService {

	List<CategoryDto> findAllCategory();
	CategoryDto findCategoryById(int id);
	CategoryDto addCategory(@Valid CategoryDto c);
	Boolean deleteCategory(int categoryId);

}
