package pl.javastart.restoffers.Category;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;
    private CategoryDtoMapper categoryDtoMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryDtoMapper categoryDtoMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryDtoMapper = categoryDtoMapper;
    }

    List<CategoryDto> findAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryDtoMapper::map)
                .toList();
    }

    List<String> getAllCategoriesNames() {
        return categoryRepository.findAll()
                .stream()
                .map(Category::getName)
                .toList();
    }

    CategoryDto saveCategory(CategoryDto categoryDto) {
        if (categoryRepository.findByName(categoryDto.getName()).isPresent()) {
            return null;
        } else {
            Category categoryToSave = categoryDtoMapper.map(categoryDto);
            Category savedCategory = categoryRepository.save(categoryToSave);
            return categoryDtoMapper.map(savedCategory);
        }
    }

    public boolean deleteCategory(Long id) {
        if (categoryRepository.findById(id).isPresent()) {
            categoryRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
