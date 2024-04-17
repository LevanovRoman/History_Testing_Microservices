package com.myapp.historytestmicroservice.controller;

import com.myapp.historytestmicroservice.QuestionNotFoundException;
import com.myapp.historytestmicroservice.model.Category;
import com.myapp.historytestmicroservice.model.Question;
import com.myapp.historytestmicroservice.model.QuestionWrapper;
import com.myapp.historytestmicroservice.model.Response;
import com.myapp.historytestmicroservice.service.CategoryService;
import com.myapp.historytestmicroservice.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("question")
public class QuestionController {

    private final QuestionService questionService;

    private final CategoryService categoryService;


//    @GetMapping("/questions/generate")
//    public String pageForGenerate(Model model){
//        List<Category> categoryList = categoryService.getAllCategory();
//        model.addAttribute("categoryList", categoryList);
//        return "question-start";
//    }
//
//    @PostMapping("/questions/generate")
//    public String getQuestionsForQuiz(@RequestParam("quiz-title") String quizTitle,
//                                      @RequestParam("numQ") Integer numQuestions,
//                                      @RequestParam("cat") String categoryName,
//                                      Model model, RedirectAttributes redirectAttributes){
//        List<Integer> questions = questionService.getQuestionsForQuiz(categoryName, numQuestions);
//
//        List<QuestionWrapper> questionsForUser = questionService.getQuestionsFromId(questions);
//        model.addAttribute("quizObject", questionsForUser);
//        return "questions-quiz";
//    }
//
//    @PostMapping("/questions/commit")
//    public String getAnswer(@RequestParam("answer1") String answer1,
//                            @RequestParam("id1") String id1,
//                            @RequestParam("answer2") String answer2,
//                            @RequestParam("id2") String id2,
//                            @RequestParam("answer3") String answer3,
//                            @RequestParam("id3") String id3,
//                            Model model, RedirectAttributes redirectAttributes) {
//        System.out.println(id1);
//        List<Response> responseList = new ArrayList<>();
//        responseList.add(new Response(Integer.parseInt(id1), answer1));
//        responseList.add(new Response(Integer.parseInt(id2), answer2));
//        responseList.add(new Response(Integer.parseInt(id3), answer3));
//
//        System.out.println(responseList);
//
//        int result = questionService.getScore(responseList);
//        System.out.println(result);
//        model.addAttribute("result", result);
//
//        return "question-result";

//    -----------------------------------------------------
    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){
        return questionService.getQuestionsByCategory(category);
    }

    @GetMapping("getById/{id}")
    public ResponseEntity<List<String>> getQuestionById(@PathVariable Integer id){
        return questionService.getQuestionById(id);
    }

    @GetMapping("deleteById/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Integer id){
        return questionService.deleteQuestion(id);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return  questionService.addQuestion(question);
    }

    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz
    (@RequestParam String categoryName, @RequestParam Integer numQuestions ){
        System.out.println("QUESTION" + questionService.getQuestionsForQuiz(categoryName, numQuestions));
        return questionService.getQuestionsForQuiz(categoryName, numQuestions);
    }

    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds){
//        System.out.println(environment.getProperty("local.server.port"));
        return questionService.getQuestionsFromId(questionIds);
    }

    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses)
    {
        return questionService.getScore(responses);
    }

    @GetMapping("getCategories")
    public ResponseEntity<List<Category>> getCategories(){
        return categoryService.getAllCategories();
    }
//    -----------------------------------------------------
}