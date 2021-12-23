package qna.domain;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import qna.CannotDeleteException;

@Entity
public class Question extends AbstractEntity {
    private static final String EXCEPTION_MESSAGE_NO_AUTH_DELETE_QUESTION = "질문을 삭제할 권한이 없습니다.";
    private static final String EXCEPTION_MESSAGE_OTHER_PERSON_ANSWER_EXIST = "다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.";

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

    public Question setDeleted(boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public boolean validateDeleted(User loginUser) throws CannotDeleteException {
        if (!isOwner(loginUser)) {
            throw new CannotDeleteException(EXCEPTION_MESSAGE_NO_AUTH_DELETE_QUESTION);
        }
        if (isNotInAnswersOwner(loginUser)) {
            throw new CannotDeleteException(EXCEPTION_MESSAGE_OTHER_PERSON_ANSWER_EXIST);
        }
        return true;
    }

    public boolean isNotInAnswersOwner(User loginUser) {
        for (Answer answer : answers) {
            if (!answer.isOwner(loginUser)) {
                return true;
            }
        }
        return false;
    }

    public void addAnswersDeleteHistories(List<DeleteHistory> deleteHistories, LocalDateTime createDate) {
        for (Answer answer : answers) {
            answer.setDeleted(true);
            deleteHistories.add(DeleteHistory.createAnswerDeleteHistory(answer, createDate));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Question question = (Question) o;
        return deleted == question.deleted && Objects.equals(title, question.title) && Objects.equals(contents, question.contents) && Objects.equals(writer, question.writer) && Objects.equals(answers, question.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title, contents, writer, answers, deleted);
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }


}
