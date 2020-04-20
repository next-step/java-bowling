package qna.domain;

import qna.CannotDeleteException;
import qna.NotFoundException;
import qna.UnAuthorizedException;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Answer extends AbstractEntity {
    private static final String OTHER_ANSWERS = "다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.";

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

        if (Objects.isNull(writer)) {
            throw new UnAuthorizedException();
        }

        if (Objects.isNull(question)) {
            throw new NotFoundException();
        }

        this.writer = writer;
        this.question = question;
        this.contents = contents;
    }

    public DeleteHistory delete() {
        this.deleted = true;
        DeleteHistory deleteHistory = new DeleteHistory(
                ContentType.ANSWER, getId(), writer, LocalDateTime.now());
        return deleteHistory;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void isOwner(User loginUser) throws CannotDeleteException {
        if (!writer.equals(loginUser)) {
            throw new CannotDeleteException(OTHER_ANSWERS);
        }
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
