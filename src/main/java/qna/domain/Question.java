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
    }

    public Question(long id, String title, String contents) {
        super(id);
        this.title = title;
        this.contents = contents;
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
        answers.add(answer, this);
    }

    public boolean isOwner(User loginUser) {
        return writer.equals(loginUser);
    }

    public Question setDeleted(boolean deleted) {
        this.deleted = deleted;
        return this;
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

    public List<DeleteHistory> delete(User loginUser) throws CannotDeleteException {
        validateDeleteUser(loginUser);
        this.setDeleted(true);
        return this.assembleDeleteHistories(createDeleteHistory(), answers.delete(loginUser));
    }

    private void validateDeleteUser(User loginUser) throws CannotDeleteException {
        if (loginUser == null) {
            throw new IllegalArgumentException("유저 정보를 입력해 주세요.");
        }
        if (!this.isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
    }

    private DeleteHistory createDeleteHistory() {
        return new DeleteHistory(ContentType.QUESTION, this.getId(), this.getWriter(), LocalDateTime.now());
    }

    private List<DeleteHistory> assembleDeleteHistories(DeleteHistory deleteHistory, List<DeleteHistory> answerDeleteHistories) {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(deleteHistory);
        deleteHistories.addAll(answerDeleteHistories);
        return deleteHistories;
    }
}
