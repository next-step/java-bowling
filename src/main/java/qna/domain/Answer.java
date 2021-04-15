package qna.domain;

import java.time.LocalDateTime;
import qna.CannotDeleteException;
import qna.NotFoundException;
import qna.UnAuthorizedException;

import javax.persistence.*;

@Entity
public class Answer extends AbstractEntity {

  @ManyToOne(optional = false)
  @JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_writer"))
  private User writer;

  @ManyToOne(optional = false)
  @JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_to_question"))
  private Question question;

  @Lob
  private String contents;

  private boolean deleted = false;

  private static final String INVALID_DELETE = "다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.";

  public Answer() {
  }

  public Answer(User writer, Question question, String contents) {
    this(null, writer, question, contents);
  }

  public Answer(Long id, User writer, Question question, String contents) {
    super(id);

    if (writer == null) {
      throw new UnAuthorizedException();
    }

    if (question == null) {
      throw new NotFoundException();
    }

    this.writer = writer;
    this.question = question;
    this.contents = contents;
  }

  private Answer setDeleted(boolean deleted) {
    this.deleted = deleted;
    return this;
  }


  private void delete() {
    setDeleted(true);
  }

  public boolean isDeleted() {
    return deleted;
  }

  public boolean isOwner(User writer) {
    return this.writer.equals(writer);
  }

  public User getWriter() {
    return writer;
  }

  public String getContents() {
    return contents;
  }

  public void toQuestion(Question question) {
    this.question = question;
  }

  public DeleteHistory turnAnswerIntoDeleteHistory(User loginUser) {
    validateDelete(loginUser);
    delete();
    return new DeleteHistory(ContentType.ANSWER, getId(), getWriter(), LocalDateTime.now());
  }

  private void validateDelete(User loginUser) {
    if (!isOwner(loginUser)) {
      throw new CannotDeleteException(INVALID_DELETE);
    }
  }

  @Override
  public String toString() {
    return "Answer [id=" + getId() + ", writer=" + writer + ", contents=" + contents + "]";
  }

}
