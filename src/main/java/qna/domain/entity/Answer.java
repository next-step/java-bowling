package qna.domain.entity;

import qna.exception.CannotDeleteException;
import qna.exception.NotFoundException;
import qna.exception.UnAuthorizedException;

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

    public void toQuestion(Question question) {
        this.question = question;
    }

    public DeleteHistory delete(User loginUser) {
        validateUser(loginUser);
        this.deleted = true;
        return makeHistory(loginUser);
    }

    private void validateUser(User loginUser) {
        if (!this.writer.equals(loginUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변은 삭제할 수 없습니다.");
        }
    }

    private DeleteHistory makeHistory(User loginUser) throws CannotDeleteException {
        if (!this.deleted) {
            throw new CannotDeleteException("삭제 상태가 아닌 답변을 이력으로 만들 수 없습니다.");
        }
        return new DeleteHistory(this, loginUser);
    }
}
