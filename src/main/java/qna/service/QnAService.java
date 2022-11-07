package qna.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import qna.CannotDeleteException;
import qna.NotFoundException;
import qna.domain.Question;
import qna.domain.QuestionRepository;
import qna.domain.User;

@Service("qnaService")
public class QnAService {
    private static final Logger log = LoggerFactory.getLogger(QnAService.class);

    private final QuestionRepository questionRepository;
    private final ApplicationEventPublisher eventPublisher;

    public QnAService(QuestionRepository questionRepository, ApplicationEventPublisher eventPublisher) {
        this.questionRepository = questionRepository;
        this.eventPublisher = eventPublisher;
    }

    @Transactional(readOnly = true)
    public Question findQuestionById(Long id) {
        return questionRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(NotFoundException::new);
    }

    @Transactional
    public void deleteQuestion(User loginUser, long questionId) throws CannotDeleteException {
        Question question = findQuestionById(questionId);
        question.delete(loginUser, eventPublisher);
    }
}
