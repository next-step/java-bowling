package qna.domain;

import org.hibernate.annotations.Where;
import qna.CannotDeleteException;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Question extends AbstractEntity {
    public static final String NO_AUTH_FOR_DELETE_MESSAGE = "질문을 삭제할 권한이 없습니다.";

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

    public void delete(User loginUser) throws CannotDeleteException {
        checkAuthenticationForDelete(loginUser);
        checkAnswerForDelete(loginUser);

        deleted = true;

        Answers answers = getAnswers();
        answers.deleteAll();
    }

    private void checkAuthenticationForDelete(User loginUser) throws CannotDeleteException {
        if (!isOwner(loginUser)) {
            throw new CannotDeleteException(NO_AUTH_FOR_DELETE_MESSAGE);
        }
    }

    private void checkAnswerForDelete(User loginUser) throws CannotDeleteException {
        getAnswers().checkOwnerForDelete(loginUser);
    }

    public DeleteHistories makeDeleteHistories() throws CannotDeleteException {
        return new DeleteHistories(this, getAnswers());
    }

    public DeleteHistory makeDeleteHistory() {
        return new DeleteHistory(ContentType.QUESTION, getId(), getWriter(), LocalDateTime.now());
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

    public boolean isDeleted() {
        return deleted;
    }

    public Answers getAnswers() {
        return Answers.from(answers);
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
