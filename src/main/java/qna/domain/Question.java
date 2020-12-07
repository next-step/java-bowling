package qna.domain;

import qna.exception.CannotDeleteException;
import qna.exception.NotOwnedChildContentException;
import qna.exception.NotOwnedContentException;

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

    private Question() {}

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

    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        answers.addAnswer(answer);
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

    public List<DeleteHistory> delete(User loginUser) throws CannotDeleteException {

        List<DeleteHistory> deleteHistories = new ArrayList<>();

        throwIfNotOwnedQuestion(loginUser);
        throwIfHaveNotOwnedAnswer(loginUser);

        this.deleted = true;

        deleteHistories.add(DeleteHistory.initQuestion(getId(), getWriter()));
        deleteHistories.addAll(answers.deleteAll(loginUser));

        return deleteHistories;
    }

    private void throwIfNotOwnedQuestion(User loginUser) {
        if (!isOwner(loginUser)) {
            throw new NotOwnedContentException();
        }
    }

    private void throwIfHaveNotOwnedAnswer(User loginUser) {
        if (answers.hasOtherOwnedAnswer(loginUser)) {
            throw new NotOwnedChildContentException();
        }
    }

}
