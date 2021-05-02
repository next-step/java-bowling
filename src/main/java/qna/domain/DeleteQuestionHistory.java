package qna.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class DeleteQuestionHistory {
  private final List<DeleteHistory> values;

  private DeleteQuestionHistory(List<DeleteHistory> values) {
    this.values = Collections.unmodifiableList(values);
  }

  public static DeleteQuestionHistory createNew(Question question) {
    List<DeleteHistory> values = new ArrayList<>();
    values.add(DeleteHistory.newQuestion(question.getId(), question.getWriter()));
    for (Answer answer : question.getAnswers()) {
      values.add(DeleteHistory.newAnswer(answer.getId(), answer.getWriter()));
    }
    return new DeleteQuestionHistory(values);
  }

  public List<DeleteHistory> getValues() {
    return values;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    DeleteQuestionHistory that = (DeleteQuestionHistory) o;
    return Objects.equals(values, that.values);
  }

  @Override
  public int hashCode() {
    return Objects.hash(values);
  }

  @Override
  public String toString() {
    return "DeleteHistories{" +
            "values=" + values +
            '}';
  }
}
