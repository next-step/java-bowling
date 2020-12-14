package qna.domain;

import qna.CannotDeleteException;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
public class Question extends AbstractEntity {

    public static final String ERROR_DELETE_QUESTION_AUTHORITY_MISMATCH = "질문을 삭제할 권한이 없습니다.";

    @Column(length = 100, nullable = false)
    private String title;

    @Lob
    private String contents;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
    private User writer;

    @Embedded
    private Answers answers = new Answers(); // nullable

    private boolean deleted = false;

    public Question() {}

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

    public Question addAnswer(Answer answer) {
        answer.toQuestion(this);
        answers.addAnswer(answer);
        return this;
    }

    public Optional<List<Answer>> getAnswers() {
        return Optional.ofNullable(answers.getAnswers());
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

    public List<DeleteHistory> delete(User loginUser) throws CannotDeleteException {
        validateWriter(loginUser);

        if (Objects.isNull(this.answers)) {
            return historiesWithoutAnswers();
        }

        validateAnswersWriter(loginUser);
        return historiesWithAnswers();
    }

    private List<DeleteHistory> historiesWithoutAnswers() {
        return Stream.of(deleteQuestion())
                    .collect(Collectors.toList());
    }

    private List<DeleteHistory> historiesWithAnswers() {
        return Stream
                .concat(Stream.of(deleteQuestion()),
                        deleteAnswers().stream())
                .collect(Collectors.toList());
    }

    private DeleteHistory deleteQuestion() {
        this.deleted = true;
        return new DeleteHistory(ContentType.QUESTION, getId(), writer, LocalDateTime.now());
    }

    private List<DeleteHistory> deleteAnswers() {
        return this.answers.delete();
    }

    private void validateWriter(User loginUser) throws CannotDeleteException {
        if (!isOwner(loginUser)) {
            throw new CannotDeleteException(ERROR_DELETE_QUESTION_AUTHORITY_MISMATCH);
        }
    }

    private void validateAnswersWriter(User loginUser) throws CannotDeleteException {
        if (answers.isDifferentWriter(loginUser)) {
            throw new CannotDeleteException(Answer.ERROR_DELETE_ANSWER_AUTHORITY_MISMATCH);
        }
    }


    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }

}
