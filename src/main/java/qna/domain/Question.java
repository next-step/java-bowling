package qna.domain;

import qna.CannotDeleteException;
import qna.IsNotDeletedException;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Entity
public class Question extends AbstractEntity {

    private static final String NO_DELETE_ACCESS = "질문을 삭제할 권한이 없습니다.";
    private static final String IS_NOT_DELETED = "해당 질문은 삭제되지 않았습니다.";

    @Column(length = 100, nullable = false)
    private String title;

    @Lob
    private String contents;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
    private User writer;

    @Embedded
    private final Answers answers = new Answers();

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

    public User getWriter() {
        return writer;
    }

    public Question writeBy(User loginUser) {
        this.writer = loginUser;
        return this;
    }

    private void isValidatedWriter(User writer) {
        Optional.ofNullable(writer)
                .filter(this::isOwner)
                .orElseThrow(() -> new CannotDeleteException(NO_DELETE_ACCESS));
    }

    public void delete(User writer) {
        this.isValidatedWriter(writer);
        this.answers.delete(writer);
        this.deleted = true;
    }

    public DeleteHistory deleteHistory() {
        return Optional.of(this)
                .filter(Question::isDeleted)
                .map(DeleteHistory::of)
                .orElseThrow(() -> new IsNotDeletedException(IS_NOT_DELETED));
    }

    public List<DeleteHistory> deleteHistories() {
        List<DeleteHistory> deleteHistories = this.answers.deleteHistories();
        deleteHistories.add(this.deleteHistory());

        return deleteHistories;
    }

    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        answers.add(answer);
    }

    public boolean isOwner(User loginUser) {
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
