package qna.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

  public void add(Answer answer) {
    answers.add(answer);
  }

  public List<DeleteHistory> turnAnswerIntoDeleteHistory(User loginUser)
      throws CannotDeleteException {
    List<DeleteHistory> deleteHistories = new ArrayList<>();
    for (Answer answer : answers) {
      deleteHistories.add(answer.turnAnswerIntoDeleteHistory(loginUser));
    }
    return deleteHistories;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Answers answers1 = (Answers) o;
    return Objects.equals(answers, answers1.answers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(answers);
  }
}
