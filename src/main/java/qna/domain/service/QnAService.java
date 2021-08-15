package qna.domain.service;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import qna.domain.entity.Question;
import qna.domain.entity.User;
import qna.domain.exception.CannotDeleteException;
import qna.domain.exception.NotFoundException;
import qna.domain.repository.AnswerRepository;
import qna.domain.repository.QuestionRepository;

@Service("qnaService")
public class QnAService {

    private static final Logger log = LoggerFactory.getLogger(QnAService.class);

    @Resource(name = "questionRepository")
    private QuestionRepository questionRepository;

    @Resource(name = "answerRepository")
    private AnswerRepository answerRepository;

    @Resource(name = "deleteHistoryService")
    private DeleteHistoryService deleteHistoryService;

    @Transactional(readOnly = true)
    public Question findQuestionById(final Long id) {
        return questionRepository.findByIdAndDeletedFalse(id)
            .orElseThrow(NotFoundException::new);
    }

    @Transactional
    public void deleteQuestion(final User loginUser, final long questionId) throws CannotDeleteException {
        final Question question = findQuestionById(questionId);
        question.delete(loginUser);

        deleteHistoryService.deleteQuestion(question);
    }
}
