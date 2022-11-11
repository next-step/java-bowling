package qna.domain;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.stream.Collectors;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import qna.CannotDeleteException;

import static qna.domain.DeleteHistory.newDeleteHistory;

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

    public static Question newQuestion(long id, String title, String contents) {
        return new Question(id, title, contents);
    }

    public static Question newQuestionWithDeleted(Long id, String title, String contents, boolean status) {
        return new Question(id, title, contents, status);
    }

    private Question(Long id, String title, String contents, boolean status) {
        this(id, title, contents);
        this.deleted = status;
    }

    private Question(long id, String title, String contents) {
        super(id);
        this.title = title;
        this.contents = contents;
    }

    public Question writeBy(User loginUser) {
        this.writer = loginUser;
        return this;
    }

    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        answers.add(answer);
    }

    public void validOwner(User loginUser) throws CannotDeleteException {
        if (!writer.equals(loginUser)) {
            throw new CannotDeleteException("로그인한 사용자와 일치하지 않습니다");
        }
    }

    public void delete(User loginUser) throws CannotDeleteException {
        validOwner(loginUser);
        deleted = true;
        deleteAnswer();
    }

    public DeleteHistory questionHistory() {
        return newDeleteHistory(ContentType.QUESTION, this.Id(), writer, LocalDateTime.now());
    }

    public List<DeleteHistory> answersHistory() {
        return answers
            .stream()
            .map(answer -> answer.answerHistory())
            .collect(Collectors.toList());
    }

    private void deleteAnswer() throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.delete(writer);
        }
    }

    public User getWriter() {
        return writer;
    }

    @Override
    public String toString() {
        return "Question [id=" + Id() + ", title=" + title + ", contents=" + contents
            + ", writer=" + writer + "]";
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
}
