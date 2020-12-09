package qna.domain;

import qna.exception.CannotDeleteException;
import qna.exception.NotFoundException;
import qna.exception.UnAuthorizedException;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    protected Answer() {
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

    public boolean isDeleted() {
        return deleted;
    }

    public boolean isOwner(User writer) {
        return this.writer.equals(writer);
    }

    public User getWriter() {
        return writer;
    }


    public void toQuestion(Question question) {
        this.question = question;
    }


    public DeleteHistory deleteAnswer(final User loginUser) throws CannotDeleteException {
        if (!isOwner(loginUser)) {
            throw new CannotDeleteException("삭제할수 없습니다.");
        }
        this.deleted = true;
        return new DeleteHistory(ContentType.ANSWER, getId(), this.writer, LocalDateTime.now());
    }

    @Override
    public String toString() {
        return "Answer [id=" + getId() + ", writer=" + writer + ", contents=" + contents + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Answer answer = (Answer) o;
        return isDeleted() == answer.isDeleted() &&
                Objects.equals(getWriter(), answer.getWriter()) &&
                Objects.equals(question, answer.question) &&
                Objects.equals(contents, answer.contents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getWriter(), question, contents, isDeleted());
    }
}
