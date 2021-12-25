package qna.domain;

import org.hibernate.annotations.Where;
import qna.CannotDeleteException;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Embeddable
public class Answers {

  @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
  @Where(clause = "deleted = false")
  @OrderBy("id ASC")
  private List<Answer> list = new ArrayList<>();

  public void add(Answer answer) {
    list.add(answer);
  }

  public List<DeleteHistory> deleteAll(User loginUser) throws CannotDeleteException {
    return list.stream()
               .map(answer -> answer.delete(loginUser))
               .collect(toList());
  }

}
