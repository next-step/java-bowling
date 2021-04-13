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

    DeleteHistories deleteHistories = new DeleteHistories();
    deleteHistories.add(question.deleteQuestion(loginUser));

    List<Answer> answers = question.answers();
    for (Answer answer : answers) {
      answer.hasOthers(loginUser);
    }

    for (Answer answer : answers) {
      answer.setDeleted(true);
      deleteHistories.add(new DeleteHistory(answer, LocalDateTime.now()));
    }

    deleteHistoryService.saveAll(deleteHistories.history());
  }
}
