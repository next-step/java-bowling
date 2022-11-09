package qna.domain;

import org.hibernate.annotations.Where;
import qna.CannotDeleteException;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Question extends AbstractEntity {

    @Embedded
    private QuestionBody questionBody;

    @Embedded
    private QuestionWriter writer;

    @Embedded
    private final Answers answers = new Answers();

    private boolean deleted = false;

    public Question() {
    }

    public Question(String title, String contents) {
        this.questionBody = new QuestionBody(title, contents);
    }

    public Question(long id, String title, String contents) {
        super(id);
        this.questionBody = new QuestionBody(title, contents);
    }

    public User getWriter() {
        return writer.getWriter();
    }

    public Question writeBy(User loginUser) {
        this.writer = new QuestionWriter(loginUser);
        return this;
    }

    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        answers.addAnswer(answer);
    }

    public List<DeleteHistory> delete(User loginUser) throws CannotDeleteException {
        writer.checkDeleteAuthUser(loginUser);
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, getId(), writer.getWriter(), LocalDateTime.now()));
        deleteHistories.addAll(answers.delete(loginUser));
        deleted = true;
        return deleteHistories;
    }

    public boolean isDeleted() {
        return deleted;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionBody=" + questionBody +
                ", writer=" + writer +
                ", answers=" + answers +
                ", deleted=" + deleted +
                '}';
    }
}
