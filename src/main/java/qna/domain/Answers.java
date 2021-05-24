package qna.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import org.hibernate.annotations.Where;
import qna.CannotDeleteException;

@Entity
@Embeddable
public class Answers {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
  @Where(clause = "deleted = false")
  @OrderBy("id ASC")
  private final List<Answer> answers = new ArrayList<>();

  public List<Answer> of() {
    return answers;
  }

  public void add(Answer answer) {
    answers.add(answer);
  }

  public void isOwner(User loginUser) throws CannotDeleteException {
    for (Answer answer : answers) {
      if (!answer.isOwner(loginUser)) {
        throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
      }
    }
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }
}
