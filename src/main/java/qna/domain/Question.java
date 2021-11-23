package qna.domain;

import qna.CannotDeleteException;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    private boolean deleted;

    public Question() {
    }

    private Question(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    private Question(long id, String title, String contents) {
        super(id);
        this.title = title;
        this.contents = contents;
    }

    public static Question create(String title, String contents) {
        return new Question(title, contents);
    }

    public static Question create(long id, String title, String contents) {
        return new Question(id, title, contents);
    }

    public List<DeleteHistory> delete(User loginUser, long questionId) {
        validateUser(loginUser);

        List<DeleteHistory> deleteHistories = new ArrayList<>();

        this.deleted = true;

        deleteHistories.add(DeleteHistory.create(ContentType.QUESTION, questionId, writer, LocalDateTime.now()));
        deleteHistories.addAll(deleteAnswers(loginUser));
        return deleteHistories;
    }

    private List<DeleteHistory> deleteAnswers(User loginUser) {
        return answers.delete(loginUser);
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
        answers.addAnswer(answer);
    }

    public boolean isDeleted() {
        return deleted;
    }

    private void validateUser(User loginUser) throws CannotDeleteException {
        if (!isOwner(loginUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    private boolean isOwner(User loginUser) {
        return writer.equals(loginUser);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Question question = (Question) o;
        return deleted == question.deleted && Objects.equals(title, question.title) && Objects.equals(
                contents, question.contents) && Objects.equals(writer,
                                                               question.writer) && Objects.equals(
                answers, question.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title, contents, writer, answers, deleted);
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
