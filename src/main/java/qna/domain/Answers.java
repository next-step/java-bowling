package qna.domain;

import java.util.Collections;
import java.util.List;
import qna.CannotDeleteException;

public class Answers {

  private final List<Answer> answers;

  public Answers(List<Answer> answers) {
    this.answers = answers;
  }

  public void delete() {
    answers.stream().forEach(Answer::delete);
  }

  public void checkReplierIsOwner(User owner) throws CannotDeleteException {
    if (existNotOwnerReplier(owner)) {
      throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
  }

  private boolean existNotOwnerReplier(User owner) {
    return answers.stream().anyMatch(answer -> !answer.isOwner(owner));
  }

  public List<Answer> getList() {
    return Collections.unmodifiableList(answers);
  }

  public Answer getAnswer(int index) {
    return answers.get(index);
  }
}
