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
    private Answers answers;

    private boolean deleted = false;

    public Question() {
    }

    public Question(String title, String contents) {
        this.title = title;
        this.contents = contents;
        this.answers = new Answers();
    }

    public Question(long id, String title, String contents) {
        super(id);
        this.title = title;
        this.contents = contents;
        this.answers = new Answers();
    }

    public List<DeleteHistory> delete(User loginUser) throws CannotDeleteException {
        validateDeleteOfAuthority(loginUser);
        validateDeleteOfOtherUserAnswer(loginUser);
        this.deleted = true;

        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(DeleteHistory.of(ContentType.QUESTION, this.getId(), writer, LocalDateTime.now()));
        deleteHistories.addAll(deleteAnswers(loginUser));
        return deleteHistories;
    }

    private void validateDeleteOfAuthority(User loginUser) throws CannotDeleteException {
        if (!isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
    }

    private void validateDeleteOfOtherUserAnswer(User loginUser) throws CannotDeleteException {
        if (answers.isOther(loginUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    private List<DeleteHistory> deleteAnswers(User loginUser) {
        return answers.delete(loginUser);
    }

    public String getTitle() {
        return title;
    }

    public Question setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContents() {
        return contents;
    }

    public Question setContents(String contents) {
        this.contents = contents;
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

    public boolean isDeleted() {
        return deleted;
    }

    public List<Answer> getAnswers() {
        return answers.answers();
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
