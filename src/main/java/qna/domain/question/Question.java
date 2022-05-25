package qna.domain.question;

import qna.domain.AbstractEntity;
import qna.domain.answer.Answer;
import qna.domain.answer.Answers;
import qna.domain.deleteHistory.DeleteHistories;
import qna.domain.deleteHistory.DeleteHistory;
import qna.domain.user.User;
import qna.exception.CannotDeleteException;
import qna.exception.IsNotDeletedException;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.util.Optional;

@Entity
public class Question extends AbstractEntity {

    private static final String NO_DELETE_ACCESS = "질문을 삭제할 권한이 없습니다.";
    private static final String IS_NOT_DELETED = "해당 질문은 삭제되지 않았습니다.";

    @Embedded
    private final QuestionInfo questionInfo;

    @Embedded
    private final Answers answers = new Answers();

    private boolean deleted = false;

    public Question() {
        this.questionInfo = new QuestionInfo();
    }

    public Question(String title, String contents) {
        this.questionInfo = new QuestionInfo(title, contents);
    }

    public Question(long id, String title, String contents) {
        super(id);
        this.questionInfo = new QuestionInfo(title, contents);
    }

    public User getWriter() {
        return this.questionInfo.writer();
    }

    public Question writeBy(User loginUser) {
        this.questionInfo.writeBy(loginUser);
        return this;
    }

    public void delete(User writer) {
        this.isValidatedWriter(writer);
        this.answers.delete(writer);
        this.deleted = true;
    }

    private void isValidatedWriter(User writer) {
        Optional.ofNullable(writer)
                .filter(this::isOwner)
                .orElseThrow(() -> new CannotDeleteException(NO_DELETE_ACCESS));
    }

    public DeleteHistory deleteHistory() {
        return Optional.of(this)
                .filter(Question::isDeleted)
                .map(DeleteHistory::of)
                .orElseThrow(() -> new IsNotDeletedException(IS_NOT_DELETED));
    }

    public DeleteHistories deleteHistories() {
        DeleteHistories deleteHistories = this.answers.deleteHistories();
        deleteHistories.add(this.deleteHistory());

        return deleteHistories;
    }

    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        answers.add(answer);
    }

    public boolean isOwner(User loginUser) {
        return this.questionInfo.writer().equals(loginUser);
    }

    public boolean isDeleted() {
        return deleted;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionInfo=" + questionInfo +
                ", answers=" + answers +
                ", deleted=" + deleted +
                '}';
    }
}
