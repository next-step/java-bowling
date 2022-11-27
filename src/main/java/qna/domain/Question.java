package qna.domain;

import qna.CannotDeleteException;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Question extends AbstractEntity {
    public static final String CANNOT_DELETE_QUESTION_MESSAGE = "질문을 삭제할 권한이 없습니다.";
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

    public List<DeleteHistory> delete(User loginUser) {
        if (!isOwner(loginUser)) {
            throw new CannotDeleteException(CANNOT_DELETE_QUESTION_MESSAGE);
        }
        deleted = true;
        return addDeleteHistories(answers.delete(loginUser));
    }

    private List<DeleteHistory> addDeleteHistories(List<DeleteHistory> deleteHistory) {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, getId(), writer, LocalDateTime.now()));
        deleteHistories.addAll(deleteHistory);
        return deleteHistories;
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

    private boolean isOwner(User loginUser) {
        return writer.equals(loginUser);
    }

    public boolean isDeleted() {
        return deleted;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
