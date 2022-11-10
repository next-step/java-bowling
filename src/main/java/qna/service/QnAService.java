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
        if (!question.isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }

        if (!question.isOwnerOfAllAnswer(loginUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }

        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(question.delete());
        deleteHistories.addAll(question.deleteAnswers());

        deleteHistoryService.saveAll(deleteHistories);
    }
}
