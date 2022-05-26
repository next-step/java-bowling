package qna.domain;

import qna.CannotDeleteException;
import qna.NotFoundException;
import qna.UnAuthorizedException;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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

    public void delete(User loginUser, List<DeleteHistory> deleteHistories) throws CannotDeleteException {
        deletableValid(loginUser);
        deleted = true;
        deleteHistories.add(new DeleteHistory(ContentType.ANSWER, getId(), writer, LocalDateTime.now()));
    }

    private void deletableValid(User loginUser) throws CannotDeleteException {
        if (deleted) {
            throw new CannotDeleteException("이미 삭제된 답변입니다.");
        }

        if(!isOwner(loginUser)) {
            throw new CannotDeleteException("답변을 삭제할 권한이 없습니다.");
        }
    }

    private boolean isOwner(User writer) {
        return this.writer.equals(writer);
    }

    protected User getWriter() {
        return writer;
    }

    public void toQuestion(Question question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Answer [id=" + getId() + ", writer=" + writer + ", contents=" + contents + "]";
    }

    protected boolean isDeleted() {
        return deleted;
    }
}
