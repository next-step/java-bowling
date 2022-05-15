package qna.domain;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import org.hibernate.annotations.Where;

public class Answers {

  @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
  @Where(clause = "deleted = false")
  @OrderBy("id ASC")
  private final Collection<Answer> answers;

  Answers(Collection<Answer> answers) {
    this.answers = answers;
  }

  Answers() {
    this.answers = new LinkedList<>();
  }

  public boolean hasAnswerNotWrittenBy(User user) {
    return answers.stream()
        .anyMatch(answer -> !answer.isWrittenBy(user));
  }

  public Answers add(Answer answer) {
    answers.add(answer);
    return this;
  }

  public DeleteHistories deleteAllAnswerWrittenBy(User user, LocalDateTime at) {
    return new DeleteHistories(answers.stream()
        .map(answer -> answer.delete(user, at))
        .collect(Collectors.toUnmodifiableList()));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Answers other = (Answers) o;

    return answers.containsAll(other.answers);
  }
}
