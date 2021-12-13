package qna.domain;

import qna.CannotDeleteException;
import qna.UnAuthorizedException;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Answer extends AbstractEntity {
    @ManyToOne(optional = false)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_writer"))
    private User writer;

    @Lob
    private String contents;

    @Enumerated(EnumType.STRING)
    private Status status = Status.UNDELETED;

    protected Answer() {
    }

    public Answer(User writer, String contents) {
        vadlidateWriter(writer);

        this.writer = writer;
        this.contents = contents;
    }

    private void vadlidateWriter(User writer) {
        if(writer == null) {
            throw new UnAuthorizedException();
        }
    }

    public boolean isDeleted() {
        return status.isDeleted();
    }

    public void delete(User loginUser) {
        validateUser(loginUser);
        this.status = Status.DELETED;
    }

    public void validateUser(User loginUser) throws CannotDeleteException {
        if (!writer.equals(loginUser)) {
            throw new CannotDeleteException("본인이 작성한 답변만 삭제할 수 있습니다.");
        }
    }

    public DeleteHistory createDeleteHistory() {
        return DeleteHistory.ofAnswer(super.getId(), writer, LocalDateTime.now());
    }

    public User getWriter() {
        return writer;
    }

    @Override
    public String toString() {
        return "Answer [id=" + getId() + ", writer=" + writer + ", contents=" + contents + "]";
    }

}
