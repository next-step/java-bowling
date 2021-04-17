package qna.domain;

import qna.CannotDeleteException;
import qna.NotFoundException;
import qna.UnAuthorizedException;

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

    @Column
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

    public Answer beDeletedBy(User user) {
        checkIfAnswerIsOwnedBy(user);

        Answer deletedAnswer = new Answer(getId(), writer, question, contents);
        deletedAnswer.deleted = true;

        return deletedAnswer;
    }

    private void checkIfAnswerIsOwnedBy(User user) {
        if (!this.isOwner(user)) {
            throw new CannotDeleteException("답변을 작성한 사람만 지울 수 있습니다.");
        }
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

    public DeleteHistory createAnswerDeleteHistory() {
        checkIfDeleted();

        return new DeleteHistory(ContentType.ANSWER, getId(), this.writer, LocalDateTime.now());
    }

    private void checkIfDeleted() {
        if (!this.deleted) {
            throw new IllegalStateException("삭제된 상태일 때만 히스토리를 만들 수 있습니다.");
        }
    }

    @Override
    public String toString() {
        return "Answer [id=" + getId() + ", writer=" + writer + ", contents=" + contents + "]";
    }
}
