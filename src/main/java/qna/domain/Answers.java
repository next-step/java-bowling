package qna.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import org.hibernate.annotations.Where;
import qna.CannotDeleteException;

@Embeddable
public class Answers {

  @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
  @Where(clause = "deleted = false")
  @OrderBy("id ASC")
  private List<Answer> answers = new ArrayList<>();

  public Answers(final List<Answer> answers) {
    this.answers = answers;
  }

  public Answers() {
  }

  public List<DeleteHistory> deleteAnswers() {
    return answers.stream()
        .map(Answer::delete)
        .collect(Collectors.toList());
  }

  public void add(final Answer answer) {
    answers.add(answer);
  }

  public void isOwners(final User loginUser) throws CannotDeleteException {
    for (Answer answer : answers) {
      isOwner(loginUser, answer);
    }
  }

  private void isOwner(final User loginUser, final Answer answer) throws CannotDeleteException {
    if (!answer.isOwner(loginUser)) {
      throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
  }
}