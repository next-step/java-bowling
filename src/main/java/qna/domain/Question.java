package qna.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Question extends AbstractEntity {
    @Column(length = 100, nullable = false)
    private String title;

    @Lob
    private String contents;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
    private User writer;

    private final Answers answers;

    private boolean deleted = false;

    public Question() {
        answers = new Answers();
    }

    public Question(String title, String contents) {
        this.title = title;
        this.contents = contents;
        answers = new Answers();
    }

    public Question(long id, String title, String contents) {
        super(id);
        this.title = title;
        this.contents = contents;
        answers = new Answers();
    }

    public String getTitle() {
        return title;
    }

    public Question setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContents() {
        return contents;
    }

    public Question setContents(String contents) {
        this.contents = contents;
        return this;
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

    public Question setDeleted(boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public List<Answer> getAnswers() {
        return answers.answers();
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }

    public List<DeleteHistory> delete(User loginUser) {
        if (!isOwner(loginUser)) {
            throw new IllegalArgumentException("잘못된 loginUser");
        }

        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(deleteQuestion());
        deleteHistories.addAll(deleteAnswers(loginUser));
        return deleteHistories;
    }

    private List<DeleteHistory> deleteAnswers(User loginUser) {
        return answers.delete(loginUser);
    }

    private DeleteHistory deleteQuestion() {
        setDeleted(true);
        return new DeleteHistory(ContentType.QUESTION, getId(), getWriter(), LocalDateTime.now());
    }
}
