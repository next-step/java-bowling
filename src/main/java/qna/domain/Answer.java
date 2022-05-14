package qna.domain;

import java.time.LocalDateTime;
import qna.NotFoundException;
import qna.UnAuthorizedException;

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

    public boolean isWrittenBy(User user) {
        return this.writer.matchUserId(user);
    }

    public DeleteHistory delete(User user, LocalDateTime at) {
        this.deleted = true;
        return new DeleteHistory(ContentType.ANSWER, id, user, at);
    }

    public boolean isDeleted() {
        return this.deleted;
    }

    @Override
    public String toString() {
        return "Answer [id=" + id + ", writer=" + writer + ", contents=" + contents + "]";
    }
}
