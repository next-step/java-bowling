package qna.domain;

import org.hibernate.annotations.Where;
import qna.CannotDeleteException;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private Answers answers = new Answers();

    private boolean deleted = false;

    public Question() {
    }

    public Question(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public Question(long id, String title, String contents) {
        super(id);
        this.title = title;
        this.contents = contents;
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
        answers.addAnswer(answer);
    }

    public boolean isOwner(User loginUser) {
        return writer.equals(loginUser);
    }

    public void delete() {
        this.deleted = true;
    }

    public void checkAnswersRemovable() throws CannotDeleteException {
        this.answers.checkRemovable(writer);
    }

    public void deleteAnswers() {
        answers.delete();
    }

    public DeleteHistory toQuestionDeleteHistory() {
        return new DeleteHistory(ContentType.QUESTION, this.getId(), this.writer, LocalDateTime.now());
    }

    public List<DeleteHistory> toAnswersDeleteHistories() {
        return answers.getDeleteHistories();
    }

    public boolean isDeleted() {
        return deleted;
    }

    public Answers getAnswers() {
        return this.answers;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
