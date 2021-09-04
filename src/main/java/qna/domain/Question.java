package qna.domain;

import qna.exception.CannotDeleteException;

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
    private Answers answers = new Answers();

    private boolean deleted = false;

    public Question() {
    }

    public Question(final String title, final String contents) {
        this.title = title;
        this.contents = contents;
    }

    public Question(final long id, final String title, final String contents) {
        super(id);
        this.title = title;
        this.contents = contents;
    }

    public void addAnswer(final Answer answer) {
        answer.toQuestion(this);
        answers.add(answer);
    }

    public List<DeleteHistory> delete(final User loginUser) throws CannotDeleteException {
        checkWriter(loginUser);

        this.deleted = true;

        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(DeleteHistory.of(this));
        deleteHistories.addAll(answers.delete(loginUser));
        return deleteHistories;
    }

    private void checkWriter(User loginUser) throws CannotDeleteException {
        if (!writer.equals(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
    }

    public User getWriter() {
        return writer;
    }

    public Question writeBy(final User loginUser) {
        this.writer = loginUser;
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
