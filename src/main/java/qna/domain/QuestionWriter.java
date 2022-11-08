package qna.domain;

import qna.CannotDeleteException;

import javax.persistence.Embeddable;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class QuestionWriter {
    public QuestionWriter() {
    }

    public QuestionWriter(User writer) {
        this.writer = writer;
    }

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
    private User writer;

    public void checkDeleteAuthUser(User loginUser) throws CannotDeleteException {
        if (isNotEqual(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
    }

    private boolean isNotEqual(User loginUser) {
        return !writer.equals(loginUser);
    }

    public User getWriter() {
        return writer;
    }
}
