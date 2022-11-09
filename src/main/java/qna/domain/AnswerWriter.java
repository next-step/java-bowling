package qna.domain;

import qna.CannotDeleteException;

import javax.persistence.Embeddable;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class AnswerWriter {
    @ManyToOne(optional = false)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_writer"))
    private User writer;

    public AnswerWriter() {
    }

    public AnswerWriter(User writer) {
        this.writer = writer;
    }

    public User getWriter() {
        return writer;
    }

    public void checkDeleteAuthUser(User writer) throws CannotDeleteException {
        if (isNotEqual(writer)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    private boolean isNotEqual(User loginUser) {
        return !writer.equals(loginUser);
    }
}
