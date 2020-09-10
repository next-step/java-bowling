package qna.domain;

import qna.global.exception.CannotDeleteException;
import qna.global.utils.QnaValidation;

import javax.persistence.*;
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

    @Embedded
    private Answers answers;

    private boolean deleted = false;

    public Question() {
    }

    public Question(String title, String contents) {
        this.title = title;
        this.contents = contents;
        this.answers = new Answers();
    }

    public Question(long id, String title, String contents) {
        super(id);
        this.title = title;
        this.contents = contents;
        this.answers = new Answers();
    }

    public Question(String title, String contents, Answers answers) {
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

    public Answers getAnswers() {
        return answers;
    }

    public boolean isOwner(User loginUser) {
        return writer.equals(loginUser);
    }

    public List<DeleteHistory> delete(User loginUser) throws CannotDeleteException {
        this.deleted = true;
        QnaValidation.validateQuestionOwner(loginUser, this);

        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(DeleteHistory.question(getId(), writer));

        QnaValidation.validateAnswer(loginUser, answers.getAnswers());
        deleteHistories.addAll(answers.deleteAnswers());

        return deleteHistories;
    }

    public boolean isDeleted() {
        return deleted;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
