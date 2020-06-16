package qna.domain;

import qna.CannotDeleteException;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
public class Question extends AbstractEntity {
    @Column(length = 100, nullable = false)
    private String title;

    @Lob
    private String contents;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
    private User writer;

    private Answers answers = new Answers();

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

    public List<DeleteHistory> deleteQnA(User loginUser, LocalDateTime createTime) throws CannotDeleteException {
        validateDeleteRequestor(loginUser);
        Stream<DeleteHistory> questionDeleteHistory = this.delete(ContentType.QUESTION, createTime);
        Stream<DeleteHistory> answersDeleteHistory = answers.delete(ContentType.ANSWER, createTime);
        return Stream.concat(questionDeleteHistory, answersDeleteHistory)
                .collect(Collectors.toList());
    }

    private void validateDeleteRequestor(User loginUser) throws CannotDeleteException {
        if (!writer.equals(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
    }

    public Stream<DeleteHistory> delete(ContentType contentType, LocalDateTime createTime) {
        this.deleted = true;
        return Stream.of(
                new DeleteHistory(contentType, getId(), getWriter(), createTime)
        );
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

    public boolean isDeleted() {
        return deleted;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }

    public Answers getAnswers() {
        return answers;
    }
}
