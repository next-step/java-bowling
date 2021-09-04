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
        Question question = findQuestionById(questionId); // DB

//         로그인 사용자와 질문한 사람이 같은 경우 삭제 가능
//        if (!question.isOwner(loginUser)) {
//            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
//        }

//        답변이 없는 경우 삭제가 가능
//        질문자와 답변글의 모든 답변자 같은 경우 삭제가 가능하다.
//        질문자와 답변자가 다른 경우 답변을 삭제 할 수 없다

//        List<Answer> answers = question.getAnswers();
//        for (Answer answer : answers) {
//            if (!answer.isOwner(loginUser)) {
//                throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
//            }
//        }

//        질문과 답변 삭제 이력에 대한 정보를 DeleteHistory를 활용해 남긴다
//        질문을 삭제할 때 답변 또한 삭제해야 하며, 답변의 삭제 또한 삭제 상태(deleted)를 변경
        List<DeleteHistory> deleteHistories = new ArrayList<>();
//        question.setDeleted(true);
//        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, questionId, question.getWriter(), LocalDateTime.now()));
//        for (Answer answer : answers) {
//            answer.setDeleted(true);
//            deleteHistories.add(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
//        }


        deleteHistoryService.saveAll(deleteHistories); // DB
    }
}
