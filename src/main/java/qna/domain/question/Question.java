package qna.domain.question;

import qna.CannotDeleteException;
import qna.domain.AbstractEntity;
import qna.domain.ContentType;
import qna.domain.answer.Answer;
import qna.domain.answer.Answers;
import qna.domain.deleteHistory.DeleteHistories;
import qna.domain.deleteHistory.DeleteHistory;
import qna.domain.user.User;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;

@Entity
public class Question extends AbstractEntity {
    private static final String DELETE_QUESTION_PERMISSION_MESSAGE = "질문을 삭제할 권한이 없습니다.";
    private static final String ANSWER_OTHER_WRITTEN_MESSAGE = "다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.";

    @Embedded
    private final Answers answers = new Answers();

    @Embedded
    private final QuestionBody questionBody;

    private boolean deleted = false;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
    private User writer;

    public Question() {
        this(QuestionBody.DEFAULT_TITLE, QuestionBody.DEFAULT_CONTENTS);
    }

    public Question(String title, String contents) {
        this(null, title, contents);
    }

    public Question(Long id, String title, String contents) {
        super(id);
        this.questionBody = new QuestionBody(title, contents);
    }

    public User getWriter() {
        return writer;
    }

    public Question writeBy(User loginUser) {
        this.writer = loginUser;
        return this;
    }

    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        answers.add(answer);
    }

    /**
     * 질문을 삭제합니다.
     *
     * @param user 확인할 유저
     * @return 생성된 삭제 기록
     * @throws CannotDeleteException 권한이 없거나 다른 사람이 쓴 답변이 있을 경우
     */
    public DeleteHistories delete(User user) throws CannotDeleteException {
        checkIsNotOwner(user);
        checkAnswerHasWrittenByOthers(user);

        setDelete();
        DeleteHistory questionDeleteHistory = super.createDeleteHistory(ContentType.QUESTION, this.writer);
        List<DeleteHistory> answerDeleteHistories = this.answers.delete();

        return new DeleteHistories(questionDeleteHistory, answerDeleteHistories);
    }

    public void setDelete() {
        this.deleted = true;
    }

    public boolean isDeleted() {
        return this.deleted;
    }

    @Override
    public String toString() {
        return "Question{" +
                "answers=" + answers +
                ", questionBody=" + questionBody +
                ", writer=" + writer +
                ", deleted=" + deleted +
                '}';
    }

    private void checkIsNotOwner(User user) throws CannotDeleteException {
        if (!isOwner(user)) {
            throw new CannotDeleteException(DELETE_QUESTION_PERMISSION_MESSAGE);
        }
    }

    private void checkAnswerHasWrittenByOthers(User user) throws CannotDeleteException {
        if (answerHasWrittenByOthers(user)) {
            throw new CannotDeleteException(ANSWER_OTHER_WRITTEN_MESSAGE);
        }
    }

    private boolean answerHasWrittenByOthers(User user) {
        return this.answers.hasWrittenByOthers(user);
    }

    private boolean isOwner(User loginUser) {
        return writer.equals(loginUser);
    }

}
