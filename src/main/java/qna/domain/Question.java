package qna.domain;

import org.hibernate.annotations.Where;
import qna.global.exception.CannotDeleteException;
import qna.global.utils.QnaValidation;

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

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private List<Answer> answers = new ArrayList<>();

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

    public Question(String title, String contents, List<Answer> answers) {
        this.title = title;
        this.contents = contents;
        this.answers = answers;
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

    public List<DeleteHistory> delete(User loginUser) throws CannotDeleteException {
        this.deleted = true;
        QnaValidation.validateQuestionOwner(loginUser, this);

        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(DeleteHistory.of(ContentType.QUESTION, getId(), writer));

        QnaValidation.validateAnswer(loginUser, answers);
        addAnswers(deleteHistories);

        return deleteHistories;
    }

    private List<DeleteHistory> addAnswers(List<DeleteHistory> deleteHistories) {
        answers.stream()
                .map(Answer::delete)
                .forEach(deleteHistories::add);
        return deleteHistories;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
