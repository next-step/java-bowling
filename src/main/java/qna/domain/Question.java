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
        userValidationDelete(user);
        if (!answers.isEmpty()) {
            answersValidationDelete(user);
        }
        this.deleted = true;
        return saveDeleteHistory(user);
    }

    private void userValidationDelete(User user) throws CannotDeleteException {
        if (!writer.equals(user)) {
            throw new CannotDeleteException(DELETE_QUESTION_EXCEPTION_MESSAGE);
        }
    }

    private void answersValidationDelete(User user) throws CannotDeleteException {
        for (Answer answer : answers) {
            if (!answer.isSameUser(user)) {
                throw new CannotDeleteException(DELETE_ANSWER_EXCEPTION_MESSAGE);
            }
        }
        deleteAnswer(user);
    }

    private List<DeleteHistory> saveDeleteHistory(User user) {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, getId(), writer, LocalDateTime.now()));
        for (Answer answer : answers) {
            deleteHistories.add(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
        }
        return deleteHistories;
    }

    private void deleteAnswer(User user) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.delete(user);
        }
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
