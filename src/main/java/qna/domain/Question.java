package qna.domain;

import org.hibernate.annotations.Where;
import org.hibernate.sql.Delete;
import qna.CannotDeleteException;
import qna.service.DeleteHistoryService;

import javax.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    private void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }

    public List<DeleteHistory> delete(User user, LocalDateTime deleteDateTime) throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(deleteQuestion(user, deleteDateTime));
        deleteHistories.addAll(deleteRelatedAnswer(user, deleteDateTime));

        return deleteHistories;
    }

    private DeleteHistory deleteQuestion(User user, LocalDateTime deleteDateTime) throws CannotDeleteException {
        if (!isOwner(user)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
        setDeleted(true);

        return new DeleteHistory(ContentType.QUESTION, this.getId(), writer, deleteDateTime);
    }
    private List<DeleteHistory> deleteRelatedAnswer(User user, LocalDateTime deleteDateTime) throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : answers) {
            deleteHistories.add(answer.delete(user, deleteDateTime));
        }

        return deleteHistories;
    }
}
