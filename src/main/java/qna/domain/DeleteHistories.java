package qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DeleteHistories {

  private final List<DeleteHistory> deleteHistories;

  public DeleteHistories() {
    deleteHistories = new ArrayList<>();
  }

  public DeleteHistories(List<DeleteHistory> deleteHistories) {
    this.deleteHistories = deleteHistories;
  }

  public void add(DeleteHistory deleteHistory) {
    deleteHistories.add(deleteHistory);
  }

  public DeleteHistory createAnswerDeleteHistory(Answer answer) {
    return new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now());
  }

  public DeleteHistory createQuestionDeleteHistory(Question question) {
    return new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter(), LocalDateTime.now());
  }

  public List<DeleteHistory> of() {
    return deleteHistories;
  }
}
