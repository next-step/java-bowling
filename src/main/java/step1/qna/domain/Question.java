package step1.qna.domain;

import java.time.LocalDateTime;
import org.hibernate.annotations.Where;
import step1.qna.CannotDeleteException;

import javax.persistence.*;
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

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public User getWriter() {
        return writer;
    }

    public Question writeBy(final User loginUser) {
        this.writer = loginUser;
        return this;
    }

    public void addAnswer(final Answer answer) {
        answer.toQuestion(this);
        answers.add(answer);
    }

    public boolean isOwner(final User loginUser) {
        return writer.equals(loginUser);
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void validateAuthentication(final User loginUser) {
        if (!this.isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
    }

    public DeleteHistories delete(final User loginUser) {
        validateAuthentication(loginUser);
        this.deleted = true;

        Answers answers = Answers.from(this.answers);

        DeleteHistory deleteQuestionHistory = new DeleteHistory(ContentType.QUESTION, this.getId(),
            this.getWriter(),
            LocalDateTime.now());
        List<DeleteHistory> deleteAnswerHistories = answers.deleteAll(loginUser);
        return DeleteHistories.of(deleteQuestionHistory, deleteAnswerHistories);
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents
            + ", writer=" + writer + "]";
    }
}
