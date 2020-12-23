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

    @Transactional
    public void deleteQuestion(User loginUser, long questionId) throws CannotDeleteException {
        Question question = findQuestionById(questionId);
        List<DeleteHistory> deleteHistories = new ArrayList<>();

        deleteHistories.add(deleteOneQuestion(question, loginUser));
        deleteHistories.addAll(deleteAnswer(question, loginUser));
        deleteHistoryService.saveAll(deleteHistories);
    }

    public void validateIsOwner(User writer, User loginUser) throws CannotDeleteException {
        if (!writer.matchUserId(loginUser.getUserId())) {
            throw new CannotDeleteException("질문을 삭제할 수 없습니다.");
        }
    }

    public DeleteHistory deleteOneQuestion(Question question, User loginUser) throws CannotDeleteException {
        validateIsOwner(question.getWriter(), loginUser);
        question.setDeleted(true);
        return new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter(), LocalDateTime.now());
    }

    public List<DeleteHistory> deleteAnswer(Question question, User loginUser) throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        List<Answer> answers = question.getAnswers();

        for (Answer answer : answers) {
            validateIsOwner(answer.getWriter(), loginUser);
        }

        answers.stream()
                .forEach(a -> {
                    try {
                        validateIsOwner(a.getWriter(), loginUser);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
		        });

        for (Answer answer : answers) {
            answer.setDeleted(true);
            deleteHistories.add(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
        }
        return deleteHistories;
    }

    public void addDeleteHistory(List<DeleteHistory> deleteHistories) {
        deleteHistoryService.saveAll(deleteHistories);
    }
}
