package qna.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import qna.domain.DeleteHistories;
import qna.domain.Question;
import qna.domain.QuestionRepository;
import qna.domain.User;
import qna.exception.NotFoundException;

@Service
@Transactional(readOnly = true)
public class QnAService {

    private static final Logger log = LoggerFactory.getLogger(QnAService.class);

    private final QuestionRepository questionRepository;
    private final DeleteHistoryService deleteHistoryService;

    public QnAService(QuestionRepository questionRepository,
                      DeleteHistoryService deleteHistoryService) {
        this.questionRepository = questionRepository;
        this.deleteHistoryService = deleteHistoryService;
    }

    public Question findQuestionById(Long id) {
        return questionRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(NotFoundException::new);
    }

    @Transactional
    public void deleteQuestion(User loginUser, long questionId) {
        final Question question = findQuestionById(questionId);
        final DeleteHistories deleteHistories = question.delete(loginUser);
        deleteHistoryService.saveAll(deleteHistories.deleteHistories());
    }
}
