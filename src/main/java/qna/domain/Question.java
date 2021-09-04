package qna.domain;

import org.hibernate.annotations.Where;
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

    public List<DeleteHistory> deleteByUser(User user) throws CannotDeleteException {
        validUserAuthority(user);
        deleted = true;

        List<DeleteHistory> deleteHistories = deleteAnswersByUser(user);
        deleteHistories.add(0, new DeleteHistory(ContentType.QUESTION, getId(), writer, LocalDateTime.now()));
        return deleteHistories;
    }

    private void validUserAuthority(User user) throws CannotDeleteException {
        if (!writer.isSameUser(user)) {
            throw new CannotDeleteException("작성자만 삭제 할 수 있습니다.");
        }
    }

    private List<DeleteHistory> deleteAnswersByUser(User user) throws CannotDeleteException {
        return Answers.from(answers).delete(user);
    }

    public boolean isDeleted() {
        return deleted;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
