package qna.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import qna.CannotDeleteException;

import static java.lang.Boolean.TRUE;

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

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public DeleteHistories deleteProcess(User loginUser, DeleteHistories deleteHistories) throws CannotDeleteException {
        validate(loginUser, this);
        deleteQuestion(deleteHistories);
        deleteAnswer(deleteHistories);
        return deleteHistories;
    }

    private void deleteQuestion(DeleteHistories deleteHistories) {
        this.setDeleted(TRUE);
        deleteHistories.add(DeleteHistory.create(this));
    }

    private void deleteAnswer(DeleteHistories deleteHistories) {
        answers.get()
            .stream()
            .map(answer -> DeleteHistory.create(answer.setDeleted(TRUE)))
            .forEachOrdered(deleteHistories::add);
    }

    private void validate(User loginUser, Question question) throws CannotDeleteException {
        validateQuestionOwner(loginUser, question);
        validateAnswerOwner(loginUser);
    }

    private void validateQuestionOwner(User loginUser, Question question) throws CannotDeleteException {
        if (!question.isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
    }

    private void validateAnswerOwner(User loginUser) throws CannotDeleteException {
        answers.checkOwner(loginUser);
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
