package qna.domain;

import java.time.LocalDateTime;
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
  private final List<Answer> answers = new ArrayList<>();

  public List<Answer> answers() {
    return answers;
  }

  public void hasOthersAnswer(User loginUser) throws CannotDeleteException {
    for (Answer answer : answers) {
      answer.hasOthers(loginUser);
    }
  }

  public List<DeleteHistory> answerHistories() {
    return answers.stream()
        .map(answer -> new DeleteHistory(answer.setDeleted(true), LocalDateTime.now()))
        .collect(Collectors.toList());
  }
}
