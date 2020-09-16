package qna.domain;

import org.hibernate.annotations.Where;
import org.hibernate.sql.Delete;
import qna.CannotDeleteException;
import qna.service.DeleteHistoryService;

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

    public void delete(User user, DeleteHistoryService deleteHistoryService) throws CannotDeleteException {
        deleteQuestion(user, deleteHistoryService);
        deleteRelatedAnswer(user, deleteHistoryService);
    }

    private void deleteQuestion(User user, DeleteHistoryService deleteHistoryService) throws CannotDeleteException {
        if (!isOwner(user)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }

        saveQuestionDeletedHistory(deleteHistoryService);
        setDeleted(true);
    }
    private void deleteRelatedAnswer(User user, DeleteHistoryService deleteHistoryService) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.delete(user, deleteHistoryService);
        }
    }
    private void saveQuestionDeletedHistory(DeleteHistoryService deleteHistoryService){
        deleteHistoryService.save(new DeleteHistory(ContentType.QUESTION, this.getId(), writer, LocalDateTime.now()));
    }
}
