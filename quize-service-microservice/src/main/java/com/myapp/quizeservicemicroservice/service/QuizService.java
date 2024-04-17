package com.myapp.quizeservicemicroservice.service;

import com.myapp.quizeservicemicroservice.QuestionNotFoundException;
import com.myapp.quizeservicemicroservice.feign.QuizInterface;
import com.myapp.quizeservicemicroservice.model.QuestionWrapper;
import com.myapp.quizeservicemicroservice.model.Quiz;
import com.myapp.quizeservicemicroservice.model.Response;
import com.myapp.quizeservicemicroservice.repository.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;

    private final QuizInterface quizInterface;

    public ResponseEntity<Integer> createQuiz(String category, int numQ, String title) {
        System.out.println("QUIZ-SERVICE");
        List<Integer> questions = quizInterface.getQuestionsForQuiz(category, numQ).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);
        quizRepository.save(quiz);

        Integer responseId = quiz.getId();
        return new ResponseEntity<>(responseId, HttpStatus.OK);

//        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Quiz quiz = quizRepository.findById(id).get();
        List<Integer> questionIds = quiz.getQuestionIds();
        ResponseEntity<List<QuestionWrapper>> questions = quizInterface.getQuestionsFromId(questionIds);
        return questions;

    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        ResponseEntity<Integer> score = quizInterface.getScore(responses);
        return score;
    }

//    public int calculateResult(Integer id, List<String> resultList) throws QuestionNotFoundException {
//
//        Optional<Quiz> quiz = quizRepository.findById(id);
//        if (quiz.isPresent()) {
//            List<Question> questions = quiz.get().getQuestions();
//
//            int right = 0;
//            int i = 0;
//            System.out.println("SERVICE");
//            for (int j = 0; j < 3; j++) {
//                System.out.println(resultList.get(i));
//                System.out.println(questions.get(i).getRight_answer());
//                if (resultList.get(i).equals(questions.get(i).getRight_answer()))
//                    right++;
//                i++;
//            }
//            return right;
//        }
//        throw new QuestionNotFoundException("Could not find any quiz with ID " + id);
//    }

}
