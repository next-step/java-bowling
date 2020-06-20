package qna.domain;


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
        answers.add(answer);
    }

    public List<DeleteHistory> delete(User user) throws CannotDeleteException {
        if (!isOwner(user)) {
            throw new CannotDeleteException(user.getName() + "는 질문을 삭제할 수 있는 권한이 없습니다.");
        }

        List<DeleteHistory> deleteHistories = new ArrayList<>();

        if (!isDeleted()) {
            deleted = true;
            deleteHistories.add(new DeleteHistory(ContentType.QUESTION, getId(), user, LocalDateTime.now()));
            deleteHistories.addAll(deleteAnswers(user));
        }

        return deleteHistories;
    }

    private List<DeleteHistory> deleteAnswers(User user) throws CannotDeleteException {
        try {
            return answers.deleteAll(user);
        } catch (RuntimeException e) {
            throw new CannotDeleteException("전체 답변을 삭제할 수 없습니다.");
        }
    }

    private boolean isOwner(User loginUser) {
        return writer.equals(loginUser);
    }

    public boolean isDeleted() {
        return deleted;
    }

    public Answers getAnswers() {
        return answers;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
