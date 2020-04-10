package qna.domain;

import org.hibernate.annotations.Where;
import qna.CannotDeleteException;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
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

    public Answers getAnswers() {
        return new Answers(answers);
    }

    public boolean isDeleted() {
        return deleted;
    }

    public DeleteHistories delete(final User loginUser, final LocalDateTime now) {
        checkSameWriter(loginUser);
        DeleteHistories deleteHistories = deleteQuestion(now);
        return deleteHistories.addAll(deleteAnswers(loginUser));
    }

    private void checkSameWriter(final User loginUser) {
        if (!writer.isSelf(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
    }

    private DeleteHistories deleteQuestion(final LocalDateTime now) {
        deleted = true;
        DeleteHistory deleteHistory = new DeleteHistory(ContentType.QUESTION, getId(), writer, now);
        return new DeleteHistories(Arrays.asList(deleteHistory));
    }

    private DeleteHistories deleteAnswers(final User user) {
        return getAnswers().delete(user);
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
