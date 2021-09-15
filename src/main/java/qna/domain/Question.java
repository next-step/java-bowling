package qna.domain;

import qna.CannotDeleteException;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    public void validateAuthorAreSame(User loginUser) throws CannotDeleteException {
        if (!this.writer.equals(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
    }

    public void validateThereIsAnyonesElseAnswer(User loginUser) throws CannotDeleteException {
        try {
            this.answers.validateAuthorsAreSame(loginUser);
        } catch (CannotDeleteException exception) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    public List<DeleteHistory> deleteAllAndRelatedAnswers(User loginUser, long questionId) throws CannotDeleteException {
        this.validateAuthorAreSame(loginUser);
        this.validateThereIsAnyonesElseAnswer(loginUser);

        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(delete(questionId));
        deleteHistories.addAll(answers.deleteAll());

        return deleteHistories;
    }

    public DeleteHistory delete(long questionId) {
        this.deleted = true;
        return new DeleteHistory(ContentType.QUESTION, questionId, this.writer, LocalDateTime.now());
    }

    public boolean isDeleted() {
        return deleted;
    }


    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
