package qna.domain;

import qna.CannotDeleteException;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Question extends AbstractEntity {
    private static final String NO_AUTHORIZATION = "질문을 삭제할 권한이 없습니다.";

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

    @Transient
    private List<DeleteHistory> deleteHistories;

    protected Question() {
    }

    public Question(String title, String contents) {
        this(null, title, contents);
    }

    public Question(Long id, String title, String contents) {
        super(id);
        this.title = title;
        this.contents = contents;
        this.answers = new Answers();
        this.deleteHistories = new ArrayList<>();
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

    public Question writeBy(User loginUser) {
        this.writer = loginUser;
        return this;
    }

    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        this.answers.add(answer);
    }

    public void isOwner(User loginUser) throws CannotDeleteException {
        if(!writer.equals(loginUser)){
            throw new CannotDeleteException(NO_AUTHORIZATION);
        }
    }

    public List<DeleteHistory> delete(User loginUser) throws CannotDeleteException {
        isOwner(loginUser);
        this.deleted = true;

        deleteHistories.add(createDeleteHistory());
        deleteHistories.addAll(this.answers.deleteAnswers(loginUser));

        return deleteHistories;
    }

    private DeleteHistory createDeleteHistory() {
        return new DeleteHistory(
                    ContentType.QUESTION, getId(), writer, LocalDateTime.now());
    }

    public boolean isDeleted() {
        return deleted;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
