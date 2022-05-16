package qna.domain;

import java.util.ArrayList;
import java.util.List;
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

  public Answers() {
  }

  public void add(Answer answer) {
    answers.add(answer);
  }

  public void checkDeletePermissions(User loginUser) throws CannotDeleteException {
    for (Answer answer : answers) {
      answer.checkDeletePermissions(loginUser);
    }
  }

  public DeleteHistories deleteAndAddHistories(DeleteHistories deleteHistories) {
    for (Answer answer : answers) {
      answer.deleteAndAddHistory(deleteHistories);
    }
    return deleteHistories;
  }
}
