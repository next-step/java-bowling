package qna.domain.question;

import qna.AnswerOtherWrittenException;
import qna.DeleteQuestionPermissionException;
import qna.domain.AbstractEntity;
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
     * @throws DeleteQuestionPermissionException 질문을 삭제할 권한이 없을 경우
     * @throws AnswerOtherWrittenException       다른사람이 쓴 답변이 있을 경우
     */
    public DeleteHistories delete(User user) throws DeleteQuestionPermissionException, AnswerOtherWrittenException {
        checkIsNotOwner(user);
        checkAnswerHasWrittenByOthers(user);

        setDelete();
        DeleteHistory questionDeleteHistory = DeleteHistory.ofQuestion(this.id, this.writer);
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

    private void checkIsNotOwner(User user) throws DeleteQuestionPermissionException {
        if (!isOwner(user)) {
            throw new DeleteQuestionPermissionException();
        }
    }

    private void checkAnswerHasWrittenByOthers(User user) throws AnswerOtherWrittenException {
        if (answerHasWrittenByOthers(user)) {
            throw new AnswerOtherWrittenException();
        }
    }

    private boolean answerHasWrittenByOthers(User user) {
        return this.answers.hasWrittenByOthers(user);
    }

    private boolean isOwner(User loginUser) {
        return writer.equals(loginUser);
    }

}
