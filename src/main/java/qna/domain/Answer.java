package qna.domain;

import qna.CannotDeleteException;
import qna.NotFoundException;
import qna.UnAuthorizedException;
import qna.service.DeleteHistoryService;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    public Answer() {
    }

    public Answer(User writer, Question question, String contents) {
        this(null, writer, question, contents);
    }

    public Answer(Long id, User writer, Question question, String contents) {
        super(id);

        if(writer == null) {
            throw new UnAuthorizedException();
        }

        if(question == null) {
            throw new NotFoundException();
        }

        this.writer = writer;
        this.question = question;
        this.contents = contents;
    }

    public DeleteHistory deleteBy(User loginUser) throws CannotDeleteException {
        checkAnswerWriter(loginUser);
        setDeleted(true);

        return getDeleteHistory();
    }

    public void checkAnswerWriter(User loginUser) throws CannotDeleteException {
        if (!isOwner(loginUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    public Answer setDeleted(boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public DeleteHistory getDeleteHistory() {
        return new DeleteHistory(ContentType.ANSWER, getId(), writer, LocalDateTime.now());
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Answer answer = (Answer) o;
        return deleted == answer.deleted && Objects.equals(writer, answer.writer) && Objects.equals(question, answer.question) && Objects.equals(contents, answer.contents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), writer, question, contents, deleted);
    }
}
