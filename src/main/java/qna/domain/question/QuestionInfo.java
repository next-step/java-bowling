package qna.domain.question;

import qna.domain.user.User;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class QuestionInfo {

    @Embedded
    private QuestionBody questionBody;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
    private User writer;

    protected QuestionInfo() {
    }

    public QuestionInfo(String title, String contents) {
        this(toQuestionBody(title, contents));
    }

    QuestionInfo(QuestionBody questionBody) {
        this(questionBody, null);
    }

    QuestionInfo(QuestionBody questionBody, User writer) {
        this.questionBody = questionBody;
        this.writer = writer;
    }

    private static QuestionBody toQuestionBody(String title, String contents) {
        return new QuestionBody(title, contents);
    }

    public boolean equalsWriter(User user) {
        return this.writer.equals(user);
    }

    public User writer() {
        return this.writer;
    }

    public void writeBy(User user) {
        this.writer = user;
    }
}
