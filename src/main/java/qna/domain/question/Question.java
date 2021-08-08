package qna.domain.question;

import qna.exception.CannotDeleteException;
import qna.domain.common.AbstractEntity;
import qna.domain.answer.Answer;
import qna.domain.user.User;
import qna.dto.DeletePipe;

import javax.persistence.*;

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
    private AnswersInQuestion answers = new AnswersInQuestion();

    @Column
    private boolean deleted = false;

    //////////////////////// 구분선

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

    public Question writeBy(User loginUser) {
        this.writer = loginUser;
        return this;
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void delete(DeletePipe pipe) {
        validateDeletable(pipe.loginUser());

        this.deleted = true;
        pipe.addDeleteHistory(this);

        answers.delete(pipe);
    }

    private void validateDeletable(User longUser) {
        if (!isOwner(longUser)) {
            throw new CannotDeleteException(CannotDeleteException.ErrorCode.NOT_PERMISSIONS);
        }
        if (!answers.isOwner(longUser)) {
            throw new CannotDeleteException(CannotDeleteException.ErrorCode.EXISTS_ANSWER);
        }
    }

    public boolean isOwner(User writer) {
        return this.writer.equalsNameAndEmail(writer);
    }

    @Override
    public String toString() {
        return "Question [id=" + id() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
