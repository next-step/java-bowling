package qna.domain;

import qna.NotFoundException;
import qna.UnAuthorizedException;

import javax.persistence.*;

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

    @Column(name = "deleted", nullable = false)
    private boolean deleted;

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
        this.deleted = false;
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
    public String toString() {
        return "Answer [id=" + getId() + ", writer=" + writer + ", contents=" + contents + "]";
    }
}
