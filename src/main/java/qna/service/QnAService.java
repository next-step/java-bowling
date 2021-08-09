package qna.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import qna.domain.history.DeleteHistory;
import qna.exception.NotFoundException;
import qna.domain.question.Question;
import qna.domain.question.QuestionRepository;
import qna.domain.user.User;

import javax.annotation.Resource;
import java.util.List;

@Service("qnaService")
public class QnAService {
    @Resource(name = "questionRepository")
    private QuestionRepository questionRepository;

    @Resource(name = "deleteHistoryService")
    private DeleteHistoryService deleteHistoryService;

    @Transactional(readOnly = true)
    public Question findQuestionById(Long id) {
        return questionRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(NotFoundException::new);
    }

    @Transactional
    public void deleteQuestion(User loginUser, long questionId) {
        Question question = findQuestionById(questionId);

        List<DeleteHistory> deleteHistories = question.delete(loginUser);

        deleteHistoryService.saveAll(deleteHistories);
    }
}
