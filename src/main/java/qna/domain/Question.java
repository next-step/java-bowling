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

    @Enumerated(EnumType.STRING)
    private Status status = Status.UNDELETED;

    protected Question() {
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
        answers.add(answer);
    }

    public void deleteQuestion(User loginUser) {
        validateWriter(loginUser);
        status = Status.DELETED;

        answers.deleteAnswers(loginUser);
    }

    public void validateWriter(User loginUser) {
        if (!writer.equals(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
    }

    public boolean isDeleted() {
        return status.isDeleted();
    }

    public List<DeleteHistory> createDeleteHistories() {
        validateStatus();
        List<DeleteHistory> deleteHistories = new ArrayList<>();

        deleteHistories.add(DeleteHistory.ofQuestion(super.getId(), writer, LocalDateTime.now()));
        deleteHistories.addAll(answers.createDeleteHistoryies());

        return deleteHistories;
    }

    private void validateStatus() {
        if (!status.isDeleted()) {
            throw new FaildCreateDeleteHistoryException();
        }
    }

    public int answersCount() {
        return answers.size();
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }

}
