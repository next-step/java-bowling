package qna.domain;

import qna.CannotDeleteException;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Question extends AbstractContentsDeletableEntity {
    @Column(length = 100, nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
    private User writer;

    private final Answers answers = new Answers();

    public Question() {
    }

    public Question(String title, String contents) {
        super(contents);
        this.title = title;
    }

    public Question(long id, String title, String contents) {
        super(id, contents);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public Question setTitle(String title) {
        this.title = title;
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

    public boolean isOwnerOfAllAnswer(User user) {
        return answers.isOwnerOfAll(user);
    }

    public void validateDeletable(User loginUser) throws CannotDeleteException {
        if (!isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }

        if (!isOwnerOfAllAnswer(loginUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    @Override
    public DeleteHistory delete() {
        super.delete();
        return new DeleteHistory(ContentType.QUESTION, getId(), writer, LocalDateTime.now());
    }

    public List<DeleteHistory> deleteAnswers() {
        return answers.deleteAll();
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + getContents() + ", writer=" + writer + "]";
    }
}
