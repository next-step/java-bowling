package qna.domain;

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

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
    private User writer;
    private boolean deleted = false;

    protected Question() {
        this(QuestionBody.DEFAULT_TITLE, QuestionBody.DEFAULT_CONTENTS);
    }

    public Question(String title, String contents) {
        this.questionBody = createQuestionBody(title, contents);
    }

    public Question(long id, String title, String contents) {
        super(id);
        this.questionBody = createQuestionBody(title, contents);
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

    public boolean isOwner(User loginUser) {
        return writer.equals(loginUser);
    }

    public boolean isDeleted() {
        return deleted;
    }

    public DeleteHistories delete() {
        this.deleted = true;

        DeleteHistory questionDeleteHistory = createDeleteHistory(ContentType.QUESTION, this.writer);
        List<DeleteHistory> answerDeleteHistories = this.answers.delete();

        return new DeleteHistories(questionDeleteHistory, answerDeleteHistories);
    }

    public boolean answerHasWrittenByOthers(User loginUser) {
        return this.answers.hasWrittenByOthers(loginUser);
    }

    private QuestionBody createQuestionBody(String title, String contents) {
        return new QuestionBody(title, contents);
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
}
