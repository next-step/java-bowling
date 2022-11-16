package qna.domain;

import org.hibernate.annotations.Where;
import qna.CannotDeleteException;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Question extends AbstractEntity {

    private static final String DELETE_QUESTION_EXCEPTION_MESSAGE = "질문을 삭제할 권한이 없습니다.";
    private static final String DELETE_ANSWER_EXCEPTION_MESSAGE = "다른 사람의 답변이 달린 글은 삭제할 수 없습니다.";

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

    public Question(String title, String contents, List<Answer> answers) {
        this.title = title;
        this.contents = contents;
        this.answers = answers;
    }

    public List<DeleteHistory> delete(User user) throws CannotDeleteException {
        checkDeleteByUser(user);
        if (!answers.isEmpty()) {
            checkDeleteByAnswer(user);
        }
        this.deleted = true;
        return saveDeleteHistory(user);
    }

    private void checkDeleteByUser(User user) throws CannotDeleteException {
        if (!isOwner(user)) {
            throw new CannotDeleteException(DELETE_QUESTION_EXCEPTION_MESSAGE);
        }
    }

    private void checkDeleteByAnswer(User user) throws CannotDeleteException {
        Answers.from(answers);
        for (Answer answer : answers) {
            if (!answer.isOwner(user)) {
                throw new CannotDeleteException(DELETE_ANSWER_EXCEPTION_MESSAGE);
            }
        }
    }

    private List<DeleteHistory> saveDeleteHistory(User user) throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, getId(), writer, LocalDateTime.now()));
        if (!answers.isEmpty()) {
            for (Answer answer : answers) {
                deleteHistories.add(answer.delete(user));
            }
        }
        return deleteHistories;
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

    public Question setDeleted(boolean deleted) {
        this.deleted = deleted;
        return this;
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
}
