package qna.domain.qna.answer;

import qna.CannotDeleteException;
import qna.NotFoundException;
import qna.UnAuthorizedException;
import qna.domain.deletehistory.DeleteHistory;
import qna.domain.qna.AbstractEntity;
import qna.domain.qna.ContentType;
import qna.domain.qna.question.Question;
import qna.domain.user.User;

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

    public DeleteHistory delete(User loginUser) throws CannotDeleteException {
        validateCanDelete(loginUser);
        this.deleted = true;
        return DeleteHistory.of(ContentType.ANSWER, getId(), writer, LocalDateTime.now());
    }

    private void validateCanDelete(User loginUser) throws CannotDeleteException {
        if (!isWriter(loginUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    private boolean isWriter(User loginUser) {
        return this.writer.equalsNameAndEmail(loginUser);
    }
}
