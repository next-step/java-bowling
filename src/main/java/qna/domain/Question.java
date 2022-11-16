package qna.domain;

import qna.CannotDeleteException;

import javax.persistence.*;
import java.time.LocalDateTime;

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
        answers.add(answer);
    }

    public boolean isDeleted() {
        return deleted;
    }

    public Answers getAnswers() {
        return answers;
    }

    public DeleteHistories delete(User loginUser, LocalDateTime dateTime) throws CannotDeleteException {
        validateOwner(loginUser);

        DeleteHistories deleteHistories = delete(new DeleteHistories(), dateTime);
        deleteHistories = answers.deleteAll(deleteHistories, dateTime);
        return deleteHistories;
    }

    DeleteHistories delete(DeleteHistories deleteHistories, LocalDateTime dateTime) {
        deleted = true;
        return deleteHistories.add(new DeleteHistory(ContentType.QUESTION, getId(), writer, dateTime));
    }

    void validateOwner(User loginUser) throws CannotDeleteException {
        if (!isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
        answers.validateOwner(loginUser);
    }

    private boolean isOwner(User writer) {
        return this.writer.equals(writer);
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
