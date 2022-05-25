package qna.domain;

import qna.CannotDeleteException;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Question extends AbstractEntity {
    private static final String QUESTION_NOT_OWNER = "질문을 삭제할 권한이 없습니다.";
    private static final boolean DELETE_TRUE = true;

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

    public Answers getAnswers() {
        return answers;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }

    public DeleteHistory questionToDeleteHistory(User loginUser) {
        validDelete(loginUser);
        this.deleted = DELETE_TRUE;
        return new DeleteHistory(ContentType.QUESTION, getId(), getWriter(), LocalDateTime.now());
    }

    private void validDelete(User loginUser) {
        if (!isOwner(loginUser)) {
            throw new CannotDeleteException(QUESTION_NOT_OWNER);
        }
    }

    public List<DeleteHistory> answersToDeleteHistories(User loginUser) {
        return answers.answersToDeleteHistories(loginUser);
    }
}
