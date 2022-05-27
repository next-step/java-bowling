package qna.domain;

import qna.CannotDeleteException;
import qna.NotFoundException;
import qna.UnAuthorizedException;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Answer extends AbstractEntity {
    private static final String ANSWER_NOT_OWNER = "다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.";

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

    public Answer setDeleted(boolean deleted) {
        this.deleted = deleted;
        return this;
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

    public DeleteHistory toDeleteHistory(User loginUser) {
        validDelete(loginUser);
        this.deleted = true;
        return new DeleteHistory(ContentType.ANSWER, getId(), getWriter(), LocalDateTime.now());
    }

    private void validDelete(User loginUser) {
        if (!isOwner(loginUser)) {
            throw new CannotDeleteException(ANSWER_NOT_OWNER);
        }
    }
}
