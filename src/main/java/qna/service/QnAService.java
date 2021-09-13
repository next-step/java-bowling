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
import qna.exception.NotQuestionWriterException;
import qna.exception.OtherUserAnswerFoundException;

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
    public void deleteQuestion(User loginUser, long questionId)
        throws CannotDeleteException, NotQuestionWriterException, OtherUserAnswerFoundException {

        Question question = findQuestionById(questionId);
        question.delete(loginUser);

        //TODO: 질문을 조건을 따져가면서 삭제하기

        //TODO: 삭제한 질문및 답변을 삭제 기록 저장소에 저장하기.

//        List<Answer> answers = question.getAnswers();
//        for (Answer answer : answers) {
//            if (!answer.isOwner(loginUser)) {
//                throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
//            }
//        }

        List<DeleteHistory> deleteHistories = new ArrayList<>();
        question.setDeleted(true);
        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, questionId, question.getWriter(), LocalDateTime.now()));
        for (Answer answer : answers) {
            answer.setDeleted(true);
            deleteHistories.add(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
        }
        deleteHistoryService.saveAll(deleteHistories);
    }
}
