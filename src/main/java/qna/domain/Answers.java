package qna.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import org.hibernate.annotations.Where;
import qna.CannotDeleteException;

public class Answers {
  @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
  @Where(clause = "deleted = false")
  @OrderBy("id ASC")
  private final List<Answer> answers = new ArrayList<>();

  public void add(Answer answer) {
    this.answers.add(answer);
  }

  public boolean isOwner(User deleteUser) {
    return answers.stream()
      .allMatch(answer -> answer.isOwner(deleteUser));
  }
  public List<DeleteHistory> delete(final User loginUser) throws CannotDeleteException {
    final List<DeleteHistory> deleteHistories = new ArrayList<>();
    for (Answer answer : this.answers) {
      deleteHistories.add(answer.delete(loginUser));
    }
    return deleteHistories;
  }
}
