package qna.domain;

import org.hibernate.annotations.Where;
import qna.CannotDeleteException;

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
    private final List<Answer> originAnswers = new ArrayList<>();

    private Answers answers;
    private boolean deleted = false;

    public Question(String title, String contents) {
        this.title = title;
        this.contents = contents;
        this.answers = new Answers(originAnswers);
    }

    public Question(long id, String title, String contents) {
        super(id);
        this.title = title;
        this.contents = contents;
        this.answers = new Answers(originAnswers);
    }

    public Question() {
        this.answers = new Answers(originAnswers);
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

    public List<DeleteHistory> delete(User loginUser)
            throws CannotDeleteException {
        validateAuthorized(loginUser);
        hasAnswers(loginUser);
        setDeleted();
        return setDeleteHistories(super.getId());
    }

    private void validateAuthorized(User loginUser) throws CannotDeleteException {
        if (!writer.equals(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
    }

    private void hasAnswers(User loginUser) throws CannotDeleteException {
        answers.isOwner(loginUser);
    }

    private void setDeleted() {
        this.deleted = true;
    }

    public boolean isDeleted() {
        return deleted;
    }

    private List<DeleteHistory> setDeleteHistories(long questionId) {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, questionId, writer, LocalDateTime.now()));
        deleteHistories.addAll(answers.delete());
        return deleteHistories;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
