package qna.domain;

import qna.CannotDeleteException;
import qna.IsNotDeletedException;
import qna.NotFoundException;
import qna.UnAuthorizedException;

import javax.persistence.*;
import java.util.Optional;

@Entity
public class Answer extends AbstractEntity {

    private static final String NOT_DELETED_ACCESS = "해당 답변을 삭제할 권한이 존재하지 않습니다.";
    private static final String IS_NOT_DELETED = "해당 답변은 삭제되지 않았습니다.";

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

    private void isValidatedWriter(User writer) {
        Optional.of(writer)
                .filter(this::isOwner)
                .orElseThrow(() -> new CannotDeleteException(NOT_DELETED_ACCESS));
    }

    public void delete(User writer) {
        isValidatedWriter(writer);
        this.deleted = true;
    }

    public DeleteHistory deleteHistory() {
        return Optional.of(this)
                .filter(Answer::isDeleted)
                .map(DeleteHistory::of)
                .orElseThrow(() -> new IsNotDeletedException(IS_NOT_DELETED));
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
}
