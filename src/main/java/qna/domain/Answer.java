package qna.domain;

import qna.CannotDeleteException;
import qna.NotFoundException;
import qna.UnAuthorizedException;
import qna.common.ExceptionMessage;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    private Answers answers;

    private Answer answer;

    private boolean deleted = false;

    public Answer(Answers answers) {
        this.answers = answers;
    }

    public Answer(Answer answer) {
        this.answer = answer;
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

    public DeleteHistory delete(User loginUser) {
        if(!answer.isOwner(loginUser)) {
            throw new IllegalArgumentException(ExceptionMessage.NOT_HAVE_PERMISSION_OTHER_ANSWER);
        }
        return new DeleteHistory(ContentType.ANSWER, getId(), writer, LocalDateTime.now());
    }


    public DeleteHistory deleteAnswer(User loginUser) throws CannotDeleteException {
        if(!answer.isOwner(loginUser)) {
            throw new CannotDeleteException(ExceptionMessage.NOT_HAVE_PERMISSION_ANSWER);
        }
        return new DeleteHistory(ContentType.ANSWER, getId(), writer, LocalDateTime.now());
    }
}
