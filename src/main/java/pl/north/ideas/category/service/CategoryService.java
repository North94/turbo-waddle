package pl.north.ideas.category.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.north.ideas.category.domain.model.Category;
import pl.north.ideas.category.domain.repository.CategoryRepository;
import pl.north.ideas.category.dto.CategoryWithStatisticsDto;
import pl.north.ideas.question.domain.repository.QuestionRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional(readOnly = true)
    public Page<Category> getCategories(Pageable pageable) {
        return getCategories(null, pageable);

    }

    @Transactional(readOnly = true)
    public Page<Category> getCategories(String search, Pageable pageable) {
        if (search == null) {
            return categoryRepository.findAll(pageable);
        } else {
            return categoryRepository.findByNameContainingIgnoreCase(search, pageable);
        }
    }

    @Transactional(readOnly = true)
    public Category getCategory(UUID id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Transactional
    public Category createCategory(Category categoryRequest) {
//        Category category = new Category();
//        category.setName(categoryRequest.getName());
        return categoryRepository.save(categoryRequest);
    }

    @Transactional
    public Category updateCategory(UUID id, Category categoryRequest) {
        Category category = categoryRepository.getById(id);
        category.setName(categoryRequest.getName());
        return categoryRepository.save(category);
    }

    @Transactional
    public void deleteCategory(UUID id) {
        categoryRepository.deleteById(id);

    }

    @Transactional(readOnly = true)
    public List<CategoryWithStatisticsDto> findAllWithStatistics() {
        return categoryRepository.findAllWithStatisticsCategory();
    }
}
