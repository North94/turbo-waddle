package pl.north.ideas.category.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.north.ideas.category.domain.model.Category;
import pl.north.ideas.category.service.CategoryService;
import pl.north.ideas.common.dto.Message;


import javax.validation.Valid;
import java.util.UUID;

import static pl.north.ideas.common.controller.ControllerUtils.paging;

@Controller
@RequestMapping("/admin/categories")
public class CategoryAdminViewController {

    private final CategoryService categoryService;

    public CategoryAdminViewController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping
    public String indexView(
            @RequestParam(name = "s", required = false) String search,
            @RequestParam(name = "field", required = false, defaultValue = "id") String field,
            @RequestParam(name = "direction", required = false, defaultValue = "asc") String direction,
            @RequestParam(name = "page", required = false, defaultValue = "0") int  page,
            @RequestParam(name = "size", required = false, defaultValue = "20") int  size,
            Model model
    ){
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.fromString(direction), field);
        String reverseSort;
        if("asc".equals(direction)){
            reverseSort = "desc";
        } else {
            reverseSort = "asc";
        }
        Page<Category> categoriesPage = categoryService.getCategories(search, pageable);
        model.addAttribute("categoriesPage", categoriesPage);
        model.addAttribute("search", search);
        model.addAttribute("reverseSort", reverseSort);
        paging(model, categoriesPage);

        return "admin/category/index";

    }
    @GetMapping("{id}")
    public String editView(Model model, @PathVariable UUID id){
        model.addAttribute("category", categoryService.getCategory(id));

        return "admin/category/edit";

    }

    @PostMapping("{id}")
    public String edit(
            @PathVariable UUID id,
            @Valid @ModelAttribute("category") Category category,
            BindingResult bindingResult,
            RedirectAttributes ra,
            Model model
    ){
        if(bindingResult.hasErrors()){
            model.addAttribute("category", category);
            model.addAttribute("message", Message.error("Błąd zapisu"));
            return  "admin/category/edit";
        }
        try {
            categoryService.updateCategory(id, category);
            ra.addFlashAttribute("message", Message.info("Kategoria zapisana"));

        } catch (Exception e){
            model.addAttribute("category", category);
            model.addAttribute("message", Message.error("Nieznany błąd zapisu"));
            return  "admin/category/edit";
        }

        return  "redirect:/admin/categories";

    }
    @GetMapping("{id}/delete")
    public String deleteView(Model model, @PathVariable UUID id, RedirectAttributes ra){
        categoryService.deleteCategory(id);
        ra.addFlashAttribute("message", Message.info("Kategoria usunięta"));

        return  "redirect:/admin/categories";
    }

}
