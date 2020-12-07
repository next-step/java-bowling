package qna.domain;

import qna.CannotDeleteException;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Question extends AbstractEntity {
    @Embedded
    private QuestionArticle questionArticle;
    @Embedded
    private final Answers answers = new Answers();

    private boolean deleted = false;

    public Question() {
    }

    public Question(String title, String contents) {
        questionArticle = new QuestionArticle(title, contents);
    }

    public Question(QuestionArticle questionArticle) {
        this.questionArticle = questionArticle;
    }

    public Question(long id, String title, String contents) {
        super(id);
        questionArticle = new QuestionArticle(title, contents);
    }

    public Question(Question question) {
        this(question.questionArticle);
    }

    public Question writeBy(User loginUser) {
        this.questionArticle.writeBy(loginUser);
        return this;
    }

    User getWriter() {
        return questionArticle.getWriter();
    }

    public void addAnswer(Answer answer) {
        answers.addAnswer(this, answer);
    }

    public boolean isOwner(User loginUser) {
        return questionArticle.isOwner(loginUser);
    }

    public DeleteHistories delete(User loginUser) throws CannotDeleteException {
        validateDelete(loginUser);
        deleted = true;
        List<DeleteHistory> answersDeleteHistories = answers.deleteAll(loginUser);
        return createDeleteHistories(answersDeleteHistories);
    }

    private void validateDelete(User loginUser) throws CannotDeleteException {
        if (!isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
    }

    private DeleteHistories createDeleteHistories(List<DeleteHistory> answersDeleteHistories) {
        DeleteHistory questionDeleteHistory = new DeleteHistory(this, LocalDateTime.now());
        return DeleteHistories.of(questionDeleteHistory, answersDeleteHistories);
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionArticle=" + questionArticle +
                ", answers=" + answers +
                ", deleted=" + deleted +
                '}';
    }
}
