package qna.domain.qna.question;

import qna.CannotDeleteException;
import qna.domain.deletehistory.DeleteHistory;
import qna.domain.qna.AbstractEntity;
import qna.domain.qna.answer.Answer;
import qna.domain.qna.answer.Answers;
import qna.domain.qna.ContentType;
import qna.domain.user.User;

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
        answers = answers.add(answer);
    }

    public boolean isDeleted() {
        return deleted;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }

    public List<DeleteHistory> delete(User loginUser) throws CannotDeleteException {
        validateCanDelete(loginUser);
        List<DeleteHistory> deleteHistories = deleteAnswers(loginUser);
        deleteHistories.add(deleteQuestion(loginUser));
        return deleteHistories;
    }

    private DeleteHistory deleteQuestion(User loginUser) {
        this.deleted = true;
        return DeleteHistory.of(ContentType.QUESTION, getId(), writer, LocalDateTime.now());
    }

    private List<DeleteHistory> deleteAnswers(User loginUser) throws CannotDeleteException {
        return answers.delete(loginUser);
    }

    private void validateCanDelete(User loginUser) throws CannotDeleteException {
        if (!isWriter(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
    }

    private boolean isWriter(User loginUser) {
        return this.writer.equalsNameAndEmail(loginUser);
    }
}