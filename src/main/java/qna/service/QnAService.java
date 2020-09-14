package qna.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import qna.CannotDeleteException;
import qna.NotFoundException;
import qna.domain.Answer;
import qna.domain.AnswerRepository;
import qna.domain.DeleteHistories;
import qna.domain.Question;
import qna.domain.QuestionRepository;
import qna.domain.User;

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
        DeleteHistories deleteHistories = question.delete(loginUser, this::deleteQuestion);
        saveDeleteHistories(deleteHistories);
    }

    @Transactional
    public void deleteQuestion(Question question, List<Answer> answers) {
        answerRepository.saveAll(answers);
        questionRepository.save(question);
    }

    @Transactional
    public void saveDeleteHistories(DeleteHistories deleteHistories) {
        deleteHistoryService.saveAll(deleteHistories.getCollection());
    }
}
