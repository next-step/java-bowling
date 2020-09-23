package qna.domain;

import org.hibernate.annotations.Where;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import qna.CannotDeleteException;

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

    private void checkOwner(User loginUser) throws CannotDeleteException {
        if (!isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
    }

    private void checkAnswerOwner(User loginUser) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.checkOwner(loginUser);
        }
    }

    public List<DeleteHistory> recordForDeletedQnAHistory(long questionId) {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        setDeleted(true);
        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, questionId
                , this.getWriter(), LocalDateTime.now()));

        for (Answer answer : answers) {
            answer.dealWithAnswerDelete(deleteHistories);
        }

        return deleteHistories;
    }
/*
    @Bean
    public List<DeleteHistory> recordForDeletedQnAHistory(long questionId
            , @Value("#{jobParameters[createDate]") String createDateStr) {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        setDeleted(true);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate createDate = LocalDate.parse(createDateStr,formatter);

        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, questionId
                , this.getWriter(), createDate));

        for (Answer answer : answers) {
            answer.dealWithAnswerDelete(deleteHistories);
        }

        return deleteHistories;
    }*/

    public void delete(User loginUser) throws CannotDeleteException {
        checkOwner(loginUser);
        checkAnswerOwner(loginUser);
    }
}
