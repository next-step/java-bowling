package qna.domain;

import java.util.ArrayList;
import java.util.List;
import qna.CannotDeleteException;

public class Answers {

  private List<Answer> answers = new ArrayList<>();

  public Answers() {
  }

  public Answers(List<Answer> answers) {
    this.answers = answers;
  }

  public void add(Answer answer) {
    answers.add(answer);
  }

  public List<DeleteHistory> deleteBy(User user) throws CannotDeleteException {
    if (!canDelete(user)) {
      throw new CannotDeleteException("답변을 삭제할 권한이 없습니다.");
    }

    List<DeleteHistory> deleteHistories = new ArrayList<>();
    for (Answer answer : answers) {
      deleteHistories.add(answer.deleteBy(user));
    }

    return deleteHistories;
  }

  public boolean canDelete(User user) {
    return answers.stream()
        .reduce(
            true,
            (canDelete, answer) -> canDelete && answer.canDelete(user),
            (bool1, bool2) -> bool1 && bool2
        );
  }
}
