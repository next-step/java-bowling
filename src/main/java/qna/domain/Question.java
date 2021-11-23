package qna.domain;

import javax.persistence.*;
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

    @Embedded
    private Answers answers = new Answers();

    private boolean deleted = false;

    public Question() {
    }

    private Question(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    private Question(long id, String title, String contents) {
        super(id);
        this.title = title;
        this.contents = contents;
    }

    public static Question create(String title, String contents) {
        return new Question(title, contents);
    }

    public static Question create(long id, String title, String contents) {
        return new Question(id, title, contents);
    }

    public List<DeleteHistory> delete(User loginUser) {
        return answers.delete(loginUser);
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
        answers.addAnswer(answer);
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

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
