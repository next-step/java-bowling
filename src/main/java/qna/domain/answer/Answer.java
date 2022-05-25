package qna.domain.answer;

import qna.domain.AbstractEntity;
import qna.domain.deleteHistory.DeleteHistory;
import qna.domain.question.Question;
import qna.domain.user.User;
import qna.exception.CannotDeleteException;
import qna.exception.IsNotDeletedException;
import qna.exception.NotFoundException;
import qna.exception.UnAuthorizedException;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.util.Optional;

@Entity
public class Answer extends AbstractEntity {

    private static final String NOT_DELETED_ACCESS = "해당 답변을 삭제할 권한이 존재하지 않습니다.";
    private static final String IS_NOT_DELETED = "해당 답변은 삭제되지 않았습니다.";

    @Embedded
    private AnswerInfo answerInfo;

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

        this.answerInfo = new AnswerInfo(writer, question, contents);
    }

    public void delete(User writer) {
        isValidatedWriter(writer);
        this.deleted = true;
    }

    private void isValidatedWriter(User writer) {
        Optional.of(writer)
                .filter(this::isOwner)
                .orElseThrow(() -> new CannotDeleteException(NOT_DELETED_ACCESS));
    }

    public DeleteHistory deleteHistory() {
        return Optional.of(this)
                .filter(Answer::isDeleted)
                .map(DeleteHistory::of)
                .orElseThrow(() -> new IsNotDeletedException(IS_NOT_DELETED));
    }

    public boolean isDeleted() {
        return deleted;
    }

    public boolean isOwner(User writer) {
        return this.answerInfo.writer().equals(writer);
    }

    public User getWriter() {
        return this.answerInfo.writer();
    }

    public String getContents() {
        return this.answerInfo.contents();
    }

    public void toQuestion(Question question) {
        this.answerInfo.toQuestion(question);
    }

    @Override
    public String toString() {
        return "Answer{" +
                "answerBody=" + answerInfo +
                ", deleted=" + deleted +
                '}';
    }
}
