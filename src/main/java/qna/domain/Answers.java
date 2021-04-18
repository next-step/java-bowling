package qna.domain;

import java.util.Collections;
import java.util.List;
import qna.CannotDeleteException;

public class Answers {

  private final List<Answer> answers;

  public Answers(List<Answer> answers) {
    this.answers = answers;
  }

  public void delete(User owner) throws CannotDeleteException {
    for (Answer answer : answers) {
      answer.delete(owner);
    }
  }

  public List<Answer> getList() {
    return Collections.unmodifiableList(answers);
  }

  public Answer getAnswer(int index) {
    return answers.get(index);
  }
}
