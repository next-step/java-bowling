package qna.domain;

import org.hibernate.annotations.Where;
import qna.CannotDeleteException;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Question extends AbstractEntity {
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private final Answers answers = new Answers();
    @Column(length = 100, nullable = false)
    private final String title;
    @Lob
    private final String contents;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
    private User writer;
    private boolean deleted = false;

    Question(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public Question(long id, String title, String contents) {
        super(id);
        this.title = title;
        this.contents = contents;
    }

    public Question writeBy(User loginUser) {
        writer = loginUser;
        return this;
    }

    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        answers.add(answer);
    }

    private boolean isOwner(User loginUser) {
        return writer.equals(loginUser);
    }

    private void checkDeletable(User loginUser) throws CannotDeleteException {
        if (!isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
        answers.checkDeletable(loginUser);
    }

    public boolean isDeleted() {
        return deleted;
    }

    public List<DeleteHistory> delete(User loginUser) throws CannotDeleteException {
        checkDeletable(loginUser);

        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(delete());
        deleteHistories.addAll(
                answers.delete(loginUser)
        );
        return deleteHistories;
    }

    private DeleteHistory delete() {
        deleted = true;
        return new DeleteHistory(ContentType.QUESTION, getId(), writer, LocalDateTime.now());
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
