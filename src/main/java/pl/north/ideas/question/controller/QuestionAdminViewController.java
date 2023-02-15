package pl.north.ideas.question.controller;

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
import pl.north.ideas.question.domain.model.Question;
import pl.north.ideas.question.service.QuestionService;

import javax.validation.Valid;
import java.util.UUID;

import static pl.north.ideas.common.controller.ControllerUtils.paging;

@Controller
@RequestMapping("/admin/questions")
public class QuestionAdminViewController {

    private final QuestionService questionService;

    public QuestionAdminViewController(QuestionService questionService) {
        this.questionService = questionService;
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
        Page<Question> questionsPage = questionService.getQuestions(search, pageable);
        model.addAttribute("questionsPage", questionsPage);
        model.addAttribute("search", search);
        model.addAttribute("reverseSort", reverseSort);
        paging(model, questionsPage);

        return "admin/question/index";

    }
    @GetMapping("{id}")
    public String editView(Model model, @PathVariable UUID id){
        model.addAttribute("question", questionService.getQuestion(id));

        return "admin/question/edit";

    }

    @PostMapping("{id}")
    public String edit(
            @PathVariable UUID id,
            @Valid @ModelAttribute("question") Question question,
            BindingResult bindingResult,
            RedirectAttributes ra,
            Model model
    ){
        if(bindingResult.hasErrors()){
            model.addAttribute("category", question);
            model.addAttribute("message", Message.error("Błąd zapisu"));
            return  "admin/question/edit";
        }
        try {
            questionService.updateQuestion(id, question);
            ra.addFlashAttribute("message", Message.info("Pytanie zapisane"));

        } catch (Exception e){
            model.addAttribute("category", question);
            model.addAttribute("message", Message.error("Nieznany błąd zapisu"));
            return  "admin/question/edit";
        }

        return  "redirect:/admin/questiions";

    }
    @GetMapping("{id}/delete")
    public String deleteView(Model model, @PathVariable UUID id, RedirectAttributes ra){
        questionService.deleteQuestion(id);
        ra.addFlashAttribute("message", Message.info("Pytanie usunięte"));

        return  "redirect:/admin/questions";
    }

}
