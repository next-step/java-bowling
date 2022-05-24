package qna.domain;

import qna.CannotDeleteException;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
    private Answers answers;

    @Column
    private boolean deleted = false;

    public Question() {
    }

    public Question(String title, String contents) {
        this(null, title, contents, new Answers(), false);
    }

    public Question(Long id, String title, String contents) {
        this(id, title, contents, new Answers(), false);
    }

    public Question(Long id, String title, String contents, Answers answers, boolean deleted) {
        super(id);
        this.title = title;
        this.contents = contents;
        this.answers = answers;
        this.deleted = deleted;
    }

    public Question writeBy(User loginUser) {
        this.writer = loginUser;
        return this;
    }

    public void addAnswer(Answer answer) {
        answer.relateQuestion(this);
        answers.add(answer);
    }

    public boolean isWriter(User loginUser) {
        return writer.equals(loginUser);
    }

    public void delete(User loginUser) {
        validateWriter(loginUser);
        answers.delete(loginUser);
        delete();
    }

    private void validateWriter(User loginUser) {
        if (!isWriter(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
    }

    private void delete() {
        this.deleted = true;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public List<DeleteHistory> toDeleteHistories() {
        List<DeleteHistory> deleteHistories = answers.toDeleteHistories();
        deleteHistories.add(toDeleteHistory());
        return deleteHistories;
    }

    private DeleteHistory toDeleteHistory() {
        return DeleteHistory.of(ContentType.QUESTION, getId(), writer);
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
