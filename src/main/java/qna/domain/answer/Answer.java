package qna.domain.answer;

import qna.domain.history.DeleteHistory;
import qna.exception.CannotDeleteException;
import qna.exception.NotFoundException;
import qna.exception.UnAuthorizedException;
import qna.domain.common.AbstractEntity;
import qna.domain.user.User;
import qna.domain.question.Question;

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

    //////////////////////// 구분선

    public Answer() {
    }

    public Answer(User writer, Question question, String contents) {
        this(null, writer, question, contents);
    }

    public Answer(Long id, User writer, Question question, String contents) {
        super(id);

        validateWriterNull(writer);
        validateQuestionNull(question);

        this.writer = writer;
        this.question = question;
        this.contents = contents;

        question.addAnswer(this);
    }

    private void validateWriterNull(User writer) {
        if (writer == null) {
            throw new UnAuthorizedException();
        }
    }

    private void validateQuestionNull(Question question) {
        if (question == null) {
            throw new NotFoundException();
        }
    }

    public boolean isDeleted() {
        return deleted;
    }

    public DeleteHistory delete(User loginUser) {
        validateDeletable(loginUser);

        this.deleted = true;

        return DeleteHistory.of(this, loginUser);
    }

    private void validateDeletable(User longUser) {
        if (!isOwner(longUser)) {
            throw new CannotDeleteException(CannotDeleteException.ErrorCode.NOT_PERMISSIONS);
        }
    }

    public boolean isOwner(User writer) {
        return this.writer.equalsNameAndEmail(writer);
    }

    @Override
    public String toString() {
        return "Answer [id=" + id() + ", writer=" + writer + ", contents=" + contents + "]";
    }
}
