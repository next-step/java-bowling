package qna.domain;

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

  public Answer() {
  }

  private Answer(Long id, User writer, Question question, String contents) {
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

  public Answer(Builder builder) {
    this(builder.id, builder.writer, builder.question, builder.contents);
  }

  public static class Builder {

    private long id = 0;
    private User writer;
    private Question question;
    private String contents = "";

    public Builder(User writer, Question question) {
      this.writer = writer;
      this.question = question;
    }

    public Builder id(long id) {
      this.id = id;
      return this;
    }

    public Builder contents(String contents) {
      this.contents = contents;
      return this;
    }

    public Answer build() {
      return new Answer(this);
    }
  }

  public static Builder getBuilder(User writer, Question question) {
    return new Builder(writer, question);
  }

  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
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

  @Override
  public String toString() {
    return "Answer [id=" + getId() + ", writer=" + writer + ", contents=" + contents + "]";
  }

  public void delete(User loginUser) {
    if (!writer.equals(loginUser)) {
      throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    deleted = true;
  }
}
