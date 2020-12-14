package qna.domain.entity;

import org.hibernate.annotations.Where;
import qna.exception.CannotDeleteException;
import qna.domain.Answers;
import qna.domain.ContentType;
import qna.domain.DeleteHistory;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Question extends AbstractEntity {
    private static final String DELETE_ERROR = "질문을 삭제할 권한이 없습니다.";

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
    private Answers answers = new Answers();

    private boolean deleted = false;

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
        answer.toQuestion(this);
        answers.add(answer);
    }

    public boolean isDeleted() {
        return deleted;
    }

    public List<DeleteHistory> delete(User loginUser) throws CannotDeleteException {
        if(isNotOwner(loginUser)){
            throw new CannotDeleteException(DELETE_ERROR);
        }

        List<DeleteHistory> deleteHistories = answers.delete(loginUser);
        deleteHistories.add(0, new DeleteHistory(ContentType.QUESTION, getId(), writer, LocalDateTime.now()));

        this.deleted = true;

        return deleteHistories;
    }

    private boolean isNotOwner(User loginUser) {
        return !writer.equals(loginUser);
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }

    public User getWriter() {
        return writer;
    }
}
