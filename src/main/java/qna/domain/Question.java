package qna.domain;

import org.hibernate.annotations.Where;
import org.hibernate.sql.Delete;
import qna.CannotDeleteException;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Question extends AbstractEntity {
    public static final String NO_DELETE_AUTHORITY_QUESTION_ERROR = "질문을 삭제할 권한이 없습니다.";

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

    public List<DeleteHistory> delete(User loginUser) throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        assertUser(loginUser);

        Answers answers = new Answers(this.answers);
        answers.delete(loginUser, deleteHistories);

        this.deleted = true;

        return makeDeleteHistories(answers);
    }

    public void assertUser(User loginUser) throws CannotDeleteException {
        if (!isOwner(loginUser)) {
            throw new CannotDeleteException(NO_DELETE_AUTHORITY_QUESTION_ERROR);
        }
    }

    private List<DeleteHistory> makeDeleteHistories(Answers answers) {
        List<DeleteHistory> deleteHistories = new ArrayList<>();

        deleteHistories.add(getDeleteQuestionHistory());
        deleteHistories.addAll(answers.getDeleteAnswersHistory());

        return deleteHistories;
    }

    public DeleteHistory getDeleteQuestionHistory() {
        return DeleteHistory.makeDeleteQuestionHistory(this);
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
        return answers;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
