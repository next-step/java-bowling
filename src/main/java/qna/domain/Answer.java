package qna.domain;

import qna.CannotDeleteException;
import qna.NotFoundException;
import qna.UnAuthorizedException;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Answer extends AbstractEntity {

    private static String DELETION_DENIED_OTHER_USER_ANSWER = "다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.";

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

    public Answer(final Long id, final User writer, final Question question, final String contents) {
        super(id);
        requireNonNull(writer);
        requireNonNull(question);
        this.writer = writer;
        this.question = question;
        this.contents = contents;
    }

    public void toQuestion(final Question question) {
        this.question = question;
    }

    public DeleteHistory delete(final User loginUser) {
        validateOwner(loginUser);
        deleted = true;
        return DeleteHistory.answerOf(getId(), writer);
    }

    private void validateOwner(final User loginUser) {
        if (!writer.equals(loginUser)) {
            throw new CannotDeleteException(DELETION_DENIED_OTHER_USER_ANSWER);
        }
    }

    private void requireNonNull(final Question question) {
        if(Objects.isNull(question)) {
            throw new NotFoundException();
        }
    }

    private void requireNonNull(final User writer) {
        if(Objects.isNull(writer)) {
            throw new UnAuthorizedException();
        }
    }

    @Override
    public String toString() {
        return "Answer [id=" + getId() + ", writer=" + writer + ", contents=" + contents + "]";
    }

}
