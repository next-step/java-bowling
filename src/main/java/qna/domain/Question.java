package qna.domain;

import java.time.LocalDateTime;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import qna.CannotDeleteException;

@Entity
public class Question extends AbstractEntity implements Deletable {

  @Column(length = 100, nullable = false)
  private String title;

  @Lob
  private String contents;

  @ManyToOne
  @JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
  private User writer;

  @Embedded
  private final Answers answers = new Answers();

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

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContents() {
    return contents;
  }

  public Question setContents(String contents) {
    this.contents = contents;
    return this;
  }

  public User getWriter() {
    return writer;
  }

  public Question writtenBy(User loginUser) {
    this.writer = loginUser;
    return this;
  }

  public boolean isOwner(User loginUser) {
    return writer.equals(loginUser);
  }

  public Question setDeleted(boolean deleted) {
    this.deleted = deleted;
    return this;
  }

  public boolean isDeleted() {
    return deleted;
  }

  public void addAnswer(Answer answer) {
    answer.toQuestion(this);
    answers.addAnswer(answer);
  }

  public Answer getAnswerById(long id) {

    return answers.getAnswerById(id);
  }

  @Override
  public void delete(User loginUser) throws CannotDeleteException {
    if (!writer.equals(loginUser)) {
      throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
    }

    deleted = true;

    answers.delete(loginUser);
  }

  @Override
  public List<DeleteHistory> getDeleteHistories() {
    List<DeleteHistory> deleteHistories = new ArrayList<>();

    if (deleted) {
      deleteHistories.add(DeleteHistory.createBy(this));

      deleteHistories.addAll(answers.getDeleteHistories());
    }

    return deleteHistories;
  }

  @Override
  public String toString() {
    return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer="
        + writer + "]";
  }
}
