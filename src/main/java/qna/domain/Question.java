package qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import qna.CannotDeleteException;

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
    private Answers answers;

    private boolean deleted = false;

    protected Question() {
    }

    public Question(String title, String contents, User loginUser) {
        this.title = title;
        this.contents = contents;
        this.answers = new Answers();
        this.writeBy(loginUser);
    }

    public Question(long id, String title, String contents, User loginUser) {
        super(id);
        this.title = title;
        this.contents = contents;
        this.answers = new Answers();
        this.writeBy(loginUser);
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

    public Answer addAnswer(Answer answer) {
        answers.addAnswer(answer);
        return answer;
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

    public List<DeleteHistory> delete(User loginUser) throws CannotDeleteException {
        if (isDeleted()) {
            throw new CannotDeleteException("이미 삭제된 글입니다.");
        }
        if (!isOwner(loginUser)) {
            throw new CannotDeleteException("질문 작성자가 아니라서 삭제할 수 없습니다.");
        }
        setDeleted(true);
        List<DeleteHistory> deleteHistories = new ArrayList<>(answers.deleteAll(loginUser));
        deleteHistories.add(setDeleted(true).createDeleteHistory(loginUser));
        return deleteHistories;
    }

    private DeleteHistory createDeleteHistory(User loginUser) {
        return new DeleteHistory(ContentType.QUESTION, getId(), loginUser, LocalDateTime.now());
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
