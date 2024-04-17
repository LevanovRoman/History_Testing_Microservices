package com.myapp.fronthistorytesting.controller;

import com.myapp.fronthistorytesting.model.Question;
import com.myapp.fronthistorytesting.model.QuizDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class FrontController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("categoryList", getCategoryList());
        return "home";
    }

    @GetMapping("/questions/allQuestions")
    public String getAllQuestions(Model model) {
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = "http://localhost:8765/question-service/question/allQuestions";
        ResponseEntity<Object[]> response = restTemplate.getForEntity(resourceUrl, Object[].class);
        model.addAttribute("questionsList", response.getBody());
        model.addAttribute("categoryList", getCategoryList());
        model.addAttribute("title", "All Questions");
        return "AllQuestions";
    }

    @GetMapping("questions/{category}")
    public String getQuestionsByCategory(@PathVariable("category") String category, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = "http://localhost:8765/question-service/question/category/" + category;
        ResponseEntity<Object[]> response = restTemplate.getForEntity(resourceUrl, Object[].class);
        model.addAttribute("questionsList", response.getBody());
        model.addAttribute("categoryList", getCategoryList());
        model.addAttribute("title", "Questions for Category: " + category);
        return "AllQuestions";
    }


    @GetMapping("/questions/new")
    public String showNewForm(Model model) {
        model.addAttribute("question", new Question());
        model.addAttribute("pageTitle", "Add New Question");
        model.addAttribute("categoryList", getCategoryList());
        return "question-form";
    }

    @PostMapping("/questions/save")
    public String saveQuestion(Question question, RedirectAttributes attributes) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = "http://localhost:8765/question-service/question/add";
        HttpEntity<Question> requestEntity = new HttpEntity<>(question, headers);
        String responseEntity = restTemplate.postForObject(resourceUrl, requestEntity, String.class);
        System.out.println(responseEntity);
        attributes.addFlashAttribute("message", "The question has been saved successfully.");
        return "redirect:/questions/allQuestions";
    }

    @GetMapping("/questions/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {

        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = "http://localhost:8765/question-service/question/getById/" + id;
        ResponseEntity<List> response = restTemplate.getForEntity(resourceUrl, List.class);
        Object o = response.getBody();
        Question question = new Question(id, (String) response.getBody().get(1),
                (String) response.getBody().get(2), (String) response.getBody().get(3), (String) response.getBody().get(4),
                (String) response.getBody().get(5), (String) response.getBody().get(6), (String) response.getBody().get(7),
                (String) response.getBody().get(8));
        model.addAttribute("question", question);
        model.addAttribute("pageTitle", "Edit Question (ID: " + id + ")");
        return "question-form";
    }

    @GetMapping("/questions/delete/{id}")
    public String deleteQuestion(@PathVariable("id") Integer id, RedirectAttributes attributes) {
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = "http://localhost:8765/question-service/question/deleteById/" + id;
        ResponseEntity<String> response = restTemplate.getForEntity(resourceUrl, String.class);
        attributes.addFlashAttribute("message", "The question ID " + id + " has been deleted.");
        return "redirect:/questions/allQuestions";
    }


    @GetMapping("/questions/generate")
    public String pageForGenerate(Model model) {
        model.addAttribute("categoryList", getCategoryList());
        return "question-start";
    }

    @PostMapping("/questions/generate")
    public String getQuestionsForQuiz(@RequestParam("quiz-title") String quizTitle,
                                      @RequestParam("numQ") Integer numQuestions,
                                      @RequestParam("cat") String categoryName,
                                      Model model, RedirectAttributes redirectAttributes) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        QuizDto quizDto = new QuizDto(categoryName, numQuestions, quizTitle);
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = "http://localhost:8765/quiz-service/create";
        HttpEntity<QuizDto> requestEntity = new HttpEntity<>(quizDto, headers);
        Integer responseEntity = restTemplate.postForObject(resourceUrl, requestEntity, Integer.class);

        RestTemplate restTemplate2 = new RestTemplate();
        String fooResourceUrl = "http://localhost:8765/quiz-service/get/" + responseEntity;
        ResponseEntity<Object[]> response = restTemplate2.getForEntity(fooResourceUrl, Object[].class);
        Object[] objects = response.getBody();

        model.addAttribute("quizObject", objects);
        return "questions-quiz-allq";
    }

    public Object[] getCategoryList() {
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8765/question-service/question/getCategories";
        ResponseEntity<Object[]> response = restTemplate.getForEntity(fooResourceUrl, Object[].class);
        return response.getBody();
    }
}