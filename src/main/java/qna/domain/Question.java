package qna.domain;

import org.hibernate.annotations.Where;
import qna.CannotDeleteException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
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
    private final Answers answers = new Answers();

    private boolean deleted = false;

    protected Question() {
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

    public List<DeleteHistory> delete(User user) {
        checkWrittenBy(user);
        deleted = true;

        List<DeleteHistory> deleteHistories = answers.delete(user);
        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, getId(), user));

        return deleteHistories;
    }

    private void checkWrittenBy(User user) {
        if (!writer.equals(user)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
    }


    public boolean isDeleted() {
        return deleted;
    }

    public List<Answer> getAnswers() {
        return Collections.emptyList();
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
