package qna.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import qna.CannotDeleteException;
import java.time.LocalDateTime;
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

    public User writer() {
        return writer;
    }

    public Question writeBy(User loginUser) {
        this.writer = loginUser;
        return this;
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    public boolean isOwner(User loginUser) {
        return writer.equals(loginUser);
    }

    public Question delete(User loginUser, DeleteHistories deleteHistory) throws CannotDeleteException {
        validateDelete(loginUser);

        this.deleted = true;
        deleteHistory.addDeleteHistory(new DeleteHistory(ContentType.QUESTION, this.getId(), this.writer(), LocalDateTime.now()));
        return this;
    }

    public void deleteAnswer(DeleteHistories deleteHistories) {
        answers.deleteAnswer(deleteHistories);
    }

    public boolean isDeleted() {
        return deleted;
    }

    public boolean isAnswerEmptyByLoginUser(User loginUser) {
        return answers.isAnswerEmptyByLoginUser(loginUser);
    }

    public Answers answers() {
        return answers;
    }

    public void validateDelete(User loginUser) throws CannotDeleteException {
        if (!isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }

        if (!isAnswerEmptyByLoginUser(loginUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Question question = (Question) o;
        return deleted == question.deleted && Objects.equals(title, question.title) && Objects.equals(contents, question.contents)
            && Objects.equals(writer, question.writer) && Objects.equals(answers, question.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title, contents, writer, answers, deleted);
    }

}
