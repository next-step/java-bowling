package qna.domain.answer;

import org.hibernate.annotations.Where;
import qna.domain.ContentType;
import qna.domain.DeleteHistory;
import qna.domain.user.User;
import qna.error.CannotDeleteException;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Embeddable
public class Answers {

  @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
  @Where(clause = "deleted = false")
  @OrderBy("id ASC")
  private final List<Answer> answers;

  public Answers(){
    this(new ArrayList<>());
  }

  private Answers(List<Answer> answers){
    this.answers = answers;
  }

  public static Answers of(List<Answer> answers){
    return new Answers(answers);
  }

  public List<DeleteHistory> deleteAll(User loginUser){
    checkAnswersDeletable(loginUser);
    return answers.stream().map(answer -> answer.delete(loginUser)).collect(Collectors.toList());
  }

  public void checkAnswersDeletable(User loginUser) {
    for (Answer answer : answers) {
      answer.checkDeletable(loginUser);
    }
  }

  public Optional<Answer> head(){
    return answers.stream().findFirst();
  }

  public void add(Answer answer) {
    answers.add(answer);
  }
}
