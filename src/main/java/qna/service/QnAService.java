package qna.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import qna.exception.CannotDeleteException;
import qna.exception.NotFoundException;
import qna.domain.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service("qnaService")
public class QnAService {
    private static final Logger log = LoggerFactory.getLogger(QnAService.class);
    private static final String INVALID_QUESTION_REMOVE_PERMISSTION = "질문을 삭제할 권한이 없습니다.";
    private static final String INVALID_ANSWER_REMOVE = "다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.";


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
        validateQuestionOwner(loginUser, question);

        List<DeleteHistory> deleteHistories = new ArrayList<>();
        question.setDeleted(true);
        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, questionId, question.getWriter(), LocalDateTime.now()));

        List<Answer> answers = question.getAnswers();
        validateAnswer(loginUser, answers);
        for (Answer answer : answers) {
            answer.setDeleted(true);
            deleteHistories.add(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
        }
        deleteHistoryService.saveAll(deleteHistories);
    }

    public void validateAnswer(User loginUser, List<Answer> answers) throws CannotDeleteException {
        answers.stream()
                .filter(answer -> answers.size() != 0)
                .filter(answer -> answer.isOwner(loginUser))
                .findFirst()
                .orElseThrow(() -> new CannotDeleteException(INVALID_ANSWER_REMOVE));
    }

    public void validateQuestionOwner(User loginUser, Question question) throws CannotDeleteException {
        if (!question.isOwner(loginUser)) {
            throw new CannotDeleteException(INVALID_QUESTION_REMOVE_PERMISSTION);
        }
    }
}
