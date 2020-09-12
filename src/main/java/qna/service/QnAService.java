package qna.service;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import qna.CannotDeleteException;
import qna.NotFoundException;
import qna.domain.*;

@Service
public class QnAService {

    private static final Logger log = LoggerFactory.getLogger(QnAService.class);

    private final QuestionRepository questionRepository;

    private final AnswerRepository answerRepository;

    private final DeleteHistoryService deleteHistoryService;

    public QnAService(QuestionRepository questionRepository,
                      AnswerRepository answerRepository,
                      DeleteHistoryService deleteHistoryService) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.deleteHistoryService = deleteHistoryService;
    }

    @Transactional(readOnly = true)
    public Question findQuestionById(Long id) {
        return questionRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(NotFoundException::new);
    }

    @Transactional
    public void deleteQuestion(User loginUser, long questionId) throws CannotDeleteException {

        Question question = findQuestionById(questionId);
        question.delete(loginUser);
        questionRepository.save(question);

        Answers answers = Answers.ofQuestion(question);
        deleteAnswers(answers, loginUser);

        saveDeleteHistories(question, answers);
    }

    @Transactional
    public void deleteAnswers(Answers answers, User loginUser) throws CannotDeleteException {
        answers.delete(loginUser);
        answerRepository.saveAll(answers.getCollection());
    }

    @Transactional
    public void saveDeleteHistories(Question question, Answers answers) {
        LocalDateTime now = LocalDateTime.now();
        deleteHistoryService.saveAll(DeleteHistories.build()
                                                    .addQuestionHistory(question, now)
                                                    .addAnswersHistories(answers, now)
                                                    .getCollection());
    }
}
