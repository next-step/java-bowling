package qna.domain;

import org.hibernate.annotations.Where;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.time.LocalDateTime;
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

  public boolean hasAnotherUser(User loginUser) {
    return list.stream()
               .anyMatch(answer -> !answer.isOwner(loginUser));
  }

  public List<DeleteHistory> deletedAll() {
    return list.stream()
               .map(answer -> {
                 answer.setDeleted(true);
                 return new DeleteHistory(
                         ContentType.ANSWER,
                         answer.getId(),
                         answer.getWriter(),
                         LocalDateTime.now());
               })
               .collect(toList());
  }
}
