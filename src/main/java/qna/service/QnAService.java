package qna.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import qna.CannotDeleteException;
import qna.NotFoundException;
import qna.domain.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
    public void deleteQuestion(User loginUser, long questionId) throws CannotDeleteException {
        Question question = findQuestionById(questionId);
        checkDeletable(question, loginUser);

        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(question.setQuestionDeleted(questionId));
        deleteHistories.addAll(question.setAnswersDelete(loginUser));
        deleteHistoryService.saveAll(deleteHistories);
    }

    private void checkDeletable(Question question, User loginUser) throws CannotDeleteException {
        question.checkQuestionDeletable(loginUser);
        question.checkAnswerDeletable(loginUser);
    }
}
