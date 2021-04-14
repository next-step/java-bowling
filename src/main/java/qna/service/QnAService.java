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

    /**
     * 1. 질문 게시글 권한 체크
     * 2. 답변 중에 다른 사람이 작성한 글이 있는지 확인
     * 3. 질문 삭제처리
     * 4. 질문 삭제처리 이력 저장
     * 5. 답변 삭제처리 이력 저장
     */
    @Transactional
    public void deleteQuestion(User loginUser, long questionId) {
        Question question = findQuestionById(questionId);
        List<DeleteHistory> deleteArticles = question.deleteQuestion(loginUser);
        deleteHistoryService.saveAll(deleteArticles);
    }
}
