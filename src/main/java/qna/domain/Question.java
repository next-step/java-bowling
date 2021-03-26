package qna.domain;

import qna.CannotDeleteException;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    private boolean deleted = false;

    public Question() {
    }

    public Question(String title, String contents) {
        this.title = title;
        this.contents = contents;
        answers = new Answers();
    }

    public Question(long id, String title, String contents) {
        super(id);
        this.title = title;
        this.contents = contents;
        answers = new Answers();
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

    public void addAnswers(List<Answer> answerList) {
        for (Answer answer : answerList) {
            addAnswer(answer);
        }
    }

    public boolean isDeleted() {
        return deleted;
    }

    private boolean isOwner(User loginUser) {
        return writer.equals(loginUser);
    }

    public DeleteHistories deleteBy(User loginUser, LocalDateTime deleteDate) {
        DeleteHistories answersDeletedHistories = answers.deleteAllBy(loginUser, deleteDate);
        DeleteHistory questionDeleteHistory = deleteQuestion(loginUser, deleteDate);
        answersDeletedHistories.add(questionDeleteHistory);
        return answersDeletedHistories;
    }

    private DeleteHistory deleteQuestion(User loginUser, LocalDateTime deleteDate) {
        validateAuthority(loginUser);
        deleted = true;
        return new DeleteHistory(ContentType.QUESTION, getId(), writer, deleteDate);
    }

    private void validateAuthority(User loginUser) {
        if (!isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
