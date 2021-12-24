package qna.domain;

import qna.CannotDeleteException;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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

    public Question() {
    }

    private Question(String title, String contents) {
       this(null, title, contents);
    }

    private Question(Long id, String title, String contents) {
        super(id);
        this.title = title;
        this.contents = contents;
    }

    public static Question of(String title, String contents) {
     return new Question(title, contents);
    }

    public static Question of(Long id, String title, String contents) {
        return new Question(id, title, contents);
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

    public List<DeleteHistory> delete(User loginUser) throws CannotDeleteException {
        validWriter(loginUser);

        List<DeleteHistory> answerDeleteHistories = answers.deleteAll(loginUser);

        setDeleted(true);

        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(DeleteHistory.from(this));
        deleteHistories.addAll(answerDeleteHistories);
        Collections.sort(deleteHistories);

        return deleteHistories;
    }

    private void validWriter(User loginUser) throws CannotDeleteException {
        if (!isOwner(loginUser)) {
            throw new CannotDeleteException("작성자와 다르므로 삭제할 수 없습니다.");
        }
    }

    public boolean isOwner(User loginUser) {
        return writer.equals(loginUser);
    }

    private Question setDeleted(boolean deleted) {
        this.deleted = deleted;
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
