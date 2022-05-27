package qna.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import qna.CannotDeleteException;
import qna.NotFoundException;
import qna.domain.*;

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
    public void deleteQuestion(User loginUser, long questionId) throws CannotDeleteException {
        Question question = findQuestionById(questionId);
        if (!question.isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }

        AnswerList answerList = new AnswerList(question.getAnswers());
        answerList.checkLoginUserEqualWithAnswersOwners(loginUser);

        DeleteHistoryList deleteHistoryList = new DeleteHistoryList();
        question.setDeleted(true);
        deleteHistoryList.add(new DeleteHistory(ContentType.QUESTION, questionId, question.getWriter(), LocalDateTime.now()));
        for (Answer answer : answerList.value()) {
            answer.setDeleted(true);
            deleteHistoryList.add(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
        }
        deleteHistoryService.saveAll(deleteHistoryList);
    }
}
