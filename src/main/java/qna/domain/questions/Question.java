package qna.domain.questions;

import qna.domain.BaseEntity;
import qna.domain.answers.Answer;
import qna.domain.answers.Answers;
import qna.domain.deleteHistory.DeleteHistories;
import qna.domain.questions.vo.Contents;
import qna.domain.questions.vo.Title;
import qna.domain.users.User;
import qna.exception.CannotDeleteException;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Question extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long id;

    @Embedded
    private Title title;

    @Embedded
    private Contents contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
    private User writer;

    @Embedded
    private Answers answers = new Answers();

    private boolean deleted = false;

    public Question() {
    }

    public Question(final String title, final String contents) {
        this(null, title, contents);
    }

    public Question(final Long id, final String title, final String contents) {
        this.id = id;
        this.title = new Title(title);
        this.contents = new Contents(contents);
    }

    public void addAnswer(final Answer answer) {
        answer.changeQuestion(this);
        answers.add(answer);
    }

    public DeleteHistories delete(final User loginUser) throws CannotDeleteException {
        checkWriter(loginUser);

        this.deleted = true;

        DeleteHistories questionHistories = DeleteHistories.of(this);
        DeleteHistories answerHistories = answers.deleteAll(loginUser);
        return DeleteHistories.concat(questionHistories, answerHistories);
    }

    private void checkWriter(final User loginUser) throws CannotDeleteException {
        if (!writer.equals(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
    }

    public Long getId() {
        return id;
    }

    public User getWriter() {
        return writer;
    }

    public Question writeBy(final User loginUser) {
        this.writer = loginUser;
        return this;
    }

    public boolean isDeleted() {
        return deleted;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Question question = (Question) o;
        return deleted == question.deleted && Objects.equals(title, question.title) && Objects.equals(contents, question.contents) && Objects.equals(writer, question.writer) && Objects.equals(answers, question.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title, contents, writer, answers, deleted);
    }
}
