package qna.domain;

import org.hibernate.annotations.Where;
import qna.CannotDeleteException;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class Question extends AbstractEntity implements DeleteHistoryRecordable {
    @Column(length = 100, nullable = false)
    private String title;

    @Lob
    private String contents;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
    private User writer;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private List<Answer> answers = new ArrayList<>();

    private boolean deleted = false;

    @Transient
    private ContentType contentType = ContentType.QUESTION;

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

    public ContentType getContentType() {
        return contentType;
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

    public DeleteHistories deleteSelf(User loginUser, LocalDateTime deleteDate) throws CannotDeleteException {
        checkIsWriter(loginUser);

        setDeleted(true);
        DeleteHistory questionDeleteHistory = DeleteHistory.from(this, deleteDate);
        DeleteHistories deleteHistories = new DeleteHistories(Collections.singletonList(questionDeleteHistory));

        DeleteHistories answerDeleteHistories = deleteAnswers(loginUser, deleteDate);
        return deleteHistories.join(answerDeleteHistories);
    }

    private DeleteHistories deleteAnswers(User loginUser, LocalDateTime deleteDate) throws CannotDeleteException {
        Answers answers = new Answers(getAnswers());
        return answers.deleteSelf(loginUser, deleteDate);
    }


    private void checkIsWriter(User loginUser) throws CannotDeleteException {
        if (!isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
    }

    public boolean isDeleted() {
        return deleted;
    }

    public Question setDeleted(boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
