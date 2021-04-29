package qna.domain;

import qna.NotFoundException;
import qna.UnAuthorizedException;

import javax.persistence.*;

@Entity
public class Answer extends AbstractEntity {

    @ManyToOne(optional = false)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_to_question"))
    private Question question;

    @Embedded
    @AssociationOverride(name = "writer", joinColumns = @JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_writer"), nullable = false))
    private PostInfo postInfo;

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

        this.postInfo = new PostInfo(contents, writer);
        this.question = question;
    }

    public Answer setDeleted(boolean deleted) {
        postInfo.delete();
        return this;
    }

    public boolean isDeleted() {
        return postInfo.isDeleted();
    }

    public boolean isOwner(User writer) {
        return postInfo.isOwner(writer);
    }

    public User getWriter() {
        return postInfo.getWriter();
    }

    public void toQuestion(Question question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Answer [id=" + getId() + ", postInfo=" + postInfo + "]";
    }
}
