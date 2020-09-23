package qna.domain;

import qna.CannotDeleteException;
import qna.NotFoundException;
import qna.UnAuthorizedException;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Answer extends AbstractEntity {
    @ManyToOne(optional = false)
    @JoinColumn(name = "writer", foreignKey = @ForeignKey(name = "fk_answer_writer"))
    private User writer;

    @ManyToOne(optional = false)
    @JoinColumn(name = " question", foreignKey = @ForeignKey(name = "fk_answer_to_question"))
    private Question question;

    @Column(name = "contents")
    private String contents;

<<<<<<< HEAD
    private boolean deleted;
=======
    @Column(name = "deleted", nullable = false)
    private boolean deleted;

    public Answer() {
    }
>>>>>>> cb44143bd22b16010852420b4bf07972ed45c0c6

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
        this.deleted = false;
<<<<<<< HEAD
    }

    public Question getQuestion() {
        return question;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public boolean deleted(boolean deleted) {
        this.deleted = deleted;
        return deleted;
    }

    public boolean isOwner(User writer) {
        return this.writer.equals(writer);
=======
>>>>>>> cb44143bd22b16010852420b4bf07972ed45c0c6
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

    public void delete() {
        isDeleted();
        //delete조건
        this.deleted = true;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public boolean isOwner(User writer) {
        return this.writer.equals(writer);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Answer answer = (Answer) o;
        return deleted == answer.deleted &&
                Objects.equals(writer, answer.writer) &&
                Objects.equals(question, answer.question) &&
                Objects.equals(contents, answer.contents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), writer, question, contents, deleted);
    }

    @Override
    public String toString() {
        return "Answer [id=" + getId() + ", writer=" + writer + ", contents=" + contents + "]";
    }

}
