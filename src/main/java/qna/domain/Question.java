package qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        Objects.requireNonNull(answer);
        answer.toQuestion(this);
        answers.add(answer);
    }

    public boolean isDeleted() {
        return deleted;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }

    public List<DeleteHistory> delete(User loginUser) throws CannotDeleteException {
        Objects.requireNonNull(loginUser, "로그인 유저가 널 입니다.");

        List<DeleteHistory> result = new ArrayList<>();
        result.add(deleteQuestion(loginUser));
        result.addAll(deleteAnswers(loginUser));

        return result;
    }

    private DeleteHistory deleteQuestion(User loginUser) throws CannotDeleteException {
        validateQuestionWriter(loginUser);
        this.deleted = true;
        return new DeleteHistory(ContentType.QUESTION, getId(), writer, LocalDateTime.now());
    }

    private void validateQuestionWriter(User loginUser) throws CannotDeleteException {
        if (!writer.equals(loginUser)) {
            throw new CannotDeleteException(String.format("질문을 삭제할 권한이 없습니다. : %s", loginUser.toString()));
        }
    }

    private List<DeleteHistory> deleteAnswers(User loginUser) throws CannotDeleteException {
        return answers.delete(loginUser);
    }
}
