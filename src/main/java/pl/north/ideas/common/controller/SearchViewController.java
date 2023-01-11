package pl.north.ideas.common.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.north.ideas.IdeasConfiguration;
import pl.north.ideas.category.service.CategoryService;
import pl.north.ideas.question.domain.model.Question;
import pl.north.ideas.question.service.AnswerService;
import pl.north.ideas.question.service.QuestionService;

import java.util.UUID;

import static pl.north.ideas.common.controller.ControllerUtils.paging;

@Controller
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchViewController extends IdeasCommonViewController {

    private final QuestionService questionService;
    private final IdeasConfiguration ideasConfiguration;


    @GetMapping
    public String searchView(
            @RequestParam(name = "query", required = false) String query,
            @RequestParam(name = "page", defaultValue = "1") int page,
            Model model
    ){
        PageRequest pageRequest = PageRequest.of(page - 1, ideasConfiguration.getPagingPageSize());

        if(query != null) {
            Page<Question> questionsPage = questionService.findByQuery(query, pageRequest);
            model.addAttribute("questionsPage", questionsPage);
            model.addAttribute("query", query);
            paging(model, questionsPage);
        }
        addGlobalAttributes(model);
        return "search/index";

    }
}
