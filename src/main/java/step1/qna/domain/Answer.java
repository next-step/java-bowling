package step1.qna.domain;

import step1.qna.CannotDeleteException;
import step1.qna.NotFoundException;
import step1.qna.UnAuthorizedException;

import javax.persistence.*;

@Entity
public class Answer extends AbstractEntity {
    private static final String EXCEPTION_MSG = "다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.";
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

    public boolean isDeleted() {
        return deleted;
    }

    public DeleteHistory delete(User loginUser) throws CannotDeleteException {
        if (!this.writer.equals(loginUser)) {
            throw new CannotDeleteException(EXCEPTION_MSG);
        }

        this.deleted = true;
        return new DeleteHistory(ContentType.ANSWER, this);
    }

    public User getWriter() {
        return writer;
    }

    public void toQuestion(Question question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Answer [id=" + getId() + ", writer=" + writer + ", contents=" + contents + "]";
    }
}