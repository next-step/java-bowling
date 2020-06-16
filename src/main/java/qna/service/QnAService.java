package qna.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import qna.CannotDeleteException;
import qna.NotFoundException;
import qna.domain.AnswerRepository;
import qna.domain.Question;
import qna.domain.QuestionRepository;
import qna.domain.User;

import javax.annotation.Resource;
import java.time.LocalDateTime;

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
    public Question findQuestionById(Long id) {
        return questionRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(NotFoundException::new);
    }

    @Transactional
    public void deleteQuestion(User loginUser, long questionId,
                               LocalDateTime createTime) throws CannotDeleteException {
        Question question = findQuestionById(questionId);
        question.deleteQnA(loginUser);
        deleteHistoryService.saveAll(question.recordDeleteHistories(createTime));
    }
}
