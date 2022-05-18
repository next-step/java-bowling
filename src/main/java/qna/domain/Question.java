package qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import qna.CannotDeleteException;

@Entity
public class Question extends AbstractEntity {

  @Column(length = 100, nullable = false)
  private String title;

  @Lob
  private String contents;

  @ManyToOne
  @JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
  private User writer;

  @Embedded
  private Answers answers = new Answers();

  private boolean deleted = false;

  public Question() {
  }

  public Question(String title, String contents) {
    this.title = title;
    this.contents = contents;
  }

  public Question(long id, String title, String contents) {
    super(id);
    this.title = title;
    this.contents = contents;
  }

  public void delete(User loginUser) {
    validateDeleteQuestion(loginUser);
    answers.delete();
    this.deleted = true;
  }

  public List<DeleteHistory> toDeleteHistories() {
    List<DeleteHistory> deleteHistories = new ArrayList<>();
    deleteHistories.add(new DeleteHistory(ContentType.QUESTION, getId(), getWriter(),
        LocalDateTime.now()));
    deleteHistories.addAll(answers.toDeleteHistories());
    return deleteHistories;
  }

  private void validateDeleteQuestion(User loginUser) {
    if (!isOwner(loginUser)) {
      throw new CannotDeleteException("작성자만 삭제 가능합니다.");
    }
  }

  public User getWriter() {
    return writer;
  }

  public Question writeBy(User loginUser) {
    this.writer = loginUser;
    return this;
  }

  public void addAnswer(Answer answer) {
    answer.toQuestion(this);
    answers.add(answer);
  }

  public boolean isOwner(User loginUser) {
    return writer.equals(loginUser);
  }

  public boolean isDeleted() {
    return deleted;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Question question = (Question) o;
    return deleted == question.deleted && Objects.equals(title, question.title)
        && Objects.equals(contents, question.contents) && Objects.equals(writer,
        question.writer);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), title, contents, writer, deleted);
  }

  @Override
  public String toString() {
    return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer="
        + writer + "]";
  }


}
