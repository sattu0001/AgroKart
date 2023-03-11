package com.app.agri.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.agri.dto.CategoryDto;
import com.app.agri.entity.Category;
import com.app.agri.entity.Product;
import com.app.agri.exception.AlreadyExistsException;
import com.app.agri.exception.ContentNotFound;
import com.app.agri.repository.CategoryRepository;
import com.app.agri.service.CategoryService;

@Transactional
@Service
public class CategoryServiceImpl extends ConversionService implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepo;

	@Override
	public List<CategoryDto> findAllCategory() {

		List<CategoryDto> categoryDto = null;
		try {
			List<Category> category = categoryRepo.findAll();

			if (category != null) {
				categoryDto = category.stream().map(c -> (CategoryDto) super.convertEntityToDto(c, CategoryDto.class))
						.collect(Collectors.toList());
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return categoryDto;
	}

	public CategoryDto findCategoryById(int id) {
		CategoryDto categoryDto = null;
		try {
			Optional<Category> optionalCategory = categoryRepo.findById(id);

			if (!optionalCategory.isPresent()) {
				throw new ContentNotFound("Category does not exists");
			} else {
				Category category = optionalCategory.get();
				categoryDto = (CategoryDto) super.convertEntityToDto(category, CategoryDto.class);
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return categoryDto;
	}

	@Override
	public CategoryDto addCategory(CategoryDto c) {
		try {
			Category category = categoryRepo.findByCategoryName(c.getCategoryName());

			if (category != null) {
				throw new AlreadyExistsException("Category is already Exists");
			}
			category = (Category) super.convertDtoToEntity(c, Category.class);

			categoryRepo.save(category);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return c;
	}

	@Override
	public Boolean deleteCategory(int categoryId) {
		try {
			Optional<Category> optionalCategory = categoryRepo.findById(categoryId);

			if (!optionalCategory.isPresent()) {
				throw new ContentNotFound("Category does not exists");
			} else {
				categoryRepo.deleteById(categoryId);

			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return Boolean.TRUE;
	}
}
