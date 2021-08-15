package qna.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import qna.exception.CannotDeleteException;
import qna.exception.NotFoundException;
import qna.exception.UnAuthorizedException;

import javax.persistence.*;
import java.util.Objects;

@Entity
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Answer extends AbstractEntity {
    @Getter
    @ManyToOne(optional = false)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_writer"))
    private User writer;

    @ManyToOne(optional = false)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_to_question"))
    private Question question;

    @Lob
    private String contents;

    private boolean deleted;

    public Answer(final User writer, final Question question, final String contents) {
        this(null, writer, question, contents);
    }

    public Answer(final Long id, final User writer, final Question question, final String contents) {
        super(id);
        verifyNull(writer, question);
        this.writer = writer;
        this.question = question;
        this.contents = contents;
    }

    public DeleteHistory deleteBy(final User writer) {
        verifyIsOwner(writer);
        this.deleted = true;
        return DeleteHistory.ofAnswer(getId(), writer);
    }

    public boolean isDeleted() {
        return deleted;
    }

    public boolean isOwner(User writer) {
        return this.writer.equals(writer);
    }

    public void toQuestion(Question question) {
        this.question = question;
    }

    private void verifyNull(final User writer, final Question question) {
        if (Objects.isNull(writer)) {
            throw new UnAuthorizedException();
        }
        if (Objects.isNull(question)) {
            throw new NotFoundException();
        }
    }

    private void verifyIsOwner(final User writer) {
        if (!isOwner(writer)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }
}
