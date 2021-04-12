package qna.domain;

import java.util.ArrayList;
import java.util.List;
import qna.CannotDeleteException;

public class DeleteHistories {

  private List<DeleteHistory> deleteHistories = new ArrayList<>();
  /*if (!question.isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }

        List<Answer> answers = question.getAnswers().getAnswers();
        //List<Answer> answers = new ArrayList<>();
        for (Answer answer : answers) {
            if (!answer.isOwner(loginUser)) {
                throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
            }
        }

        List<DeleteHistory> deleteHistories = new ArrayList<>();
        question.setDeleted(true);
        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, questionId, question.getWriter(), LocalDateTime.now()));
        for (Answer answer : answers) {
            answer.setDeleted(true);
            deleteHistories.add(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
        }*/
  public void add(User loginUser, Question question) throws CannotDeleteException {
    deleteHistories.add(question.turnQuestionIntoDeleteHistory(loginUser));
    deleteHistories.addAll(question.turnAnswerIntoDeleteHistory(loginUser));

  }

  public List<DeleteHistory> getDeleteHistories() {
    return deleteHistories;
  }
}
