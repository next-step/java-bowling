package qna.domain;

import org.hibernate.annotations.Where;
import qna.CannotDeleteException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Question extends ArticleEntity {
    @Column(length = 100, nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
    private User writer;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private List<Answer> answers = new ArrayList<>();

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

    public List<DeleteHistory> delete(User deletedBy) {
        if (!isOwner(deletedBy)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }

        List<DeleteHistory> deleteHistories = new ArrayList<>();

        setDeletedTrue();
        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, getId(), writer));

        Answers answerList = new Answers(answers);
        deleteHistories.addAll(answerList.delete(deletedBy));

        return deleteHistories;
    }

    @Override
    public String toString() {
        return "Question{" +
                "title='" + title + '\'' +
                ", writer=" + writer +
                ", answers=" + answers +
                ", contents=" + getContents() +
                ", deleted=" + isDeleted() +
                ", updatedAt=" + getUpdatedAt() +
                ", id=" + getId() +
                ", createdAt=" + getCreatedAt() +
                '}';
    }
}
