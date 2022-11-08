package qna.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import qna.CannotDeleteException;
import qna.NotFoundException;
import qna.domain.Question;
import qna.domain.QuestionRepository;
import qna.domain.User;

@Service("questionService")
public class QuestionService {
    private static final Logger log = LoggerFactory.getLogger(QuestionService.class);

    @Resource(name = "questionRepository")
    private QuestionRepository questionRepository;

    @Resource(name = "answerService")
    private AnswerService answerService;

    @Resource(name = "deleteHistoryService")
    private DeleteHistoryService deleteHistoryService;

    @Transactional(readOnly = true)
    public Question findByIdAndDeletedFalse(Long id) {
        return questionRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> {
                    log.error("[QUESTION_ERROR] 질문 조회 실패 id: {}", id);
                    throw new NotFoundException();
                });
    }

    @Transactional
    public void deleteQuestion(User loginUser, long questionId) {
        Question question = findByIdAndDeletedFalse(questionId);

        executeSoftDelete(loginUser, question);
        answerService.executeSoftDelete(loginUser, question.getAnswers());

        deleteHistoryService.saveSofeDelete(question);
    }

    public void checkOwnerOrThrow(User loginUser, Question question) {
        if (!question.isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
    }

    public Question executeSoftDelete(User loginUser, Question question) {
        checkOwnerOrThrow(loginUser, question);
        question.setDeleted(true);
        return question;
    }
}
