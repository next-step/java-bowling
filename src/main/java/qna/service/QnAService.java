package qna.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import qna.CannotDeleteException;
import qna.NotFoundException;
import qna.domain.*;

import javax.annotation.Resource;
<<<<<<< HEAD
import java.time.LocalDateTime;
=======
import java.util.Optional;
>>>>>>> cb44143bd22b16010852420b4bf07972ed45c0c6

@Service("qnaService")
public class QnAService {
    private static final Logger log = LoggerFactory.getLogger(QnAService.class);

    @Resource(name = "questionRepository")
    private QuestionRepository questionRepository;

    @Resource(name = "answerRepository")
    private AnswerRepository answerRepository;

    @Resource(name = "deleteHistoryService")
    private DeleteHistoryService deleteHistoryService;

    @Transactional
    public Question findQuestionById(Long id) {
        return questionRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(NotFoundException::new);
    }

    @Transactional
    public void deleteQuestion(User loginUser, long questionId) throws CannotDeleteException {
<<<<<<< HEAD
//        Question question = findQuestionById(questionId);
//        if (!question.isOwner(loginUser)) {
//            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
//        }
//
//        for (Answer answer : question.getAnswers()) {
//            if (!answer.isOwner(loginUser)) {
//                throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
//            }
//        }
//
//        DeleteHistories deleteHistories = new DeleteHistories();
//        question.deleted(true);
//        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, questionId, question.getWriter(), LocalDateTime.now()));
//        for (Answer answer : question.getAnswers()) {
//            answer.deleted(true);
//            deleteHistories.add(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
//        }
//       deleteHistoryService.saveAll(deleteHistories);
=======
        DeleteHistories deleteHistories = new DeleteHistories();
        Question question = findQuestionById(questionId);
        if (question == null) {
            throw new IllegalArgumentException();
        }
        if (!question.isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
        for (Answer answer : questioan.getAnswers()) {
            if (!answer.isOwner(loginUser)) {
                throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
            }
        }
        deleteQuestionHistories(question, deleteHistories);
        deleteHistoryService.saveAll(deleteHistories.getDeleteHistories());
    }

    @Transactional
    private void deleteQuestionHistories(Question question, DeleteHistories deleteHistories) {
        deleteHistories.addQuestionDeleteHistory(question);
        if (question.getAnswers() != null) {
            question.getAnswers().forEach(
                    answer -> deleteAnswerHistories(answer, deleteHistories));
        }
    }

    @Transactional
    private void deleteAnswerHistories(Answer answer, DeleteHistories deleteHistories) {
        deleteHistories.addAnswerDeleteHistory(answer);
>>>>>>> cb44143bd22b16010852420b4bf07972ed45c0c6
    }
}
