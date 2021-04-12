package qna.domain;

import java.util.ArrayList;
import java.util.List;
import qna.CannotDeleteException;

public class DeleteHistories {

  private List<DeleteHistory> deleteHistories;

  public DeleteHistories(List<DeleteHistory> deleteHistories) {
    this.deleteHistories = deleteHistories;
  }

  public static DeleteHistories of(User loginUser, Question question) throws CannotDeleteException {
    List<DeleteHistory> deleteHistories = new ArrayList<>();
    deleteHistories.add(question.turnQuestionIntoDeleteHistory(loginUser));
    deleteHistories.addAll(question.turnAnswerIntoDeleteHistory(loginUser));
    return new DeleteHistories(deleteHistories);
  }

  public List<DeleteHistory> getDeleteHistories() {
    return deleteHistories;
  }
}
