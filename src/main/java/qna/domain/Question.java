package qna.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import qna.CannotDeleteException;

@Entity
public class Question extends AbstractEntity {

    private static final String INVALID_AUTHORITY = "질문을 삭제할 권한이 없습니다.";
    private static final String INVALID_ANSWER_AUTHORITY = "다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.";

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
        answers.addAnswer(answer);
    }

    private boolean isOwner(User loginUser) {
        return writer.equals(loginUser);
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void validateDeleteAuthority(User loginUser) throws CannotDeleteException {
        if (!isOwner(loginUser)) {
            throw new CannotDeleteException(INVALID_AUTHORITY);
        }
    }

    public void validateHasAnswerAuthority(User loginUser) throws CannotDeleteException {
        if (answers.hasAnotherOwner(loginUser)) {
            throw new CannotDeleteException(INVALID_ANSWER_AUTHORITY);
        }
    }

    public void validate(User loginUser) throws CannotDeleteException {
        validateDeleteAuthority(loginUser);
        validateHasAnswerAuthority(loginUser);
    }

    public List<DeleteHistory> delete() {
        this.deleted = true;
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(new DeleteHistory(this));
        deleteHistories.addAll(this.answers.delete());
        return deleteHistories;
    }

    @Override
    public String toString() {
        return "Question{" +
            "title='" + title + '\'' +
            ", contents='" + contents + '\'' +
            ", writer=" + writer +
            ", answers=" + answers +
            ", deleted=" + deleted +
            '}';
    }
}
