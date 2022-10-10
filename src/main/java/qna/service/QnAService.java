package qna.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import qna.domain.entity.Question;
import qna.domain.entity.User;
import qna.exception.CannotDeleteException;
import qna.exception.NotFoundException;
import qna.repository.QuestionRepository;

import javax.annotation.Resource;

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
    public void deleteQuestion(User loginUser, long questionId) throws CannotDeleteException {
        Question question = findQuestionById(questionId);
        question.delete(loginUser);
        deleteHistoryService.saveAll(question.makeHistory(loginUser));
    }
}
