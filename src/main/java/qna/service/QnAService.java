package qna.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import qna.domain.deleteHistory.DeleteHistories;
import qna.domain.questions.Question;
import qna.domain.users.User;
import qna.exception.CannotDeleteException;
import qna.exception.NotFoundException;
import qna.repository.DeleteHistoryRepository;
import qna.repository.QuestionRepository;

@Service
@Transactional(readOnly = true)
public class QnAService {

    private static final Logger log = LoggerFactory.getLogger(QnAService.class);

    private final QuestionRepository questionRepository;
    private final DeleteHistoryRepository deleteHistoryRepository;

    public QnAService(QuestionRepository questionRepository, DeleteHistoryRepository deleteHistoryRepository) {
        this.questionRepository = questionRepository;
        this.deleteHistoryRepository = deleteHistoryRepository;
    }

    public Question findQuestionById(final Long id) {
        return questionRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(NotFoundException::new);
    }

    @Transactional
    public void deleteQuestion(final User loginUser, final long questionId) throws CannotDeleteException {
        Question question = findQuestionById(questionId);
        DeleteHistories deleteHistories = question.delete(loginUser);
        deleteHistoryRepository.saveAll(deleteHistories.elements());
    }
}
