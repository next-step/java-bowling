package qna.domain;

import org.hibernate.annotations.Where;
import qna.CannotDeleteException;
import qna.NotFoundDeleteHistoryException;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> answers;


    private boolean deleted = false;

    public Question() {
    }

    public Question(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public Question(long id, String title, String contents) {
        this(id, title, contents, new ArrayList<>());
    }

    public Question(long id, String title, String contents, List<Answer> answers) {
        super(id);
        this.title = title;
        this.contents = contents;
        this.answers = answers;
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

    public boolean deleted() {
        return deleted;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void delete(User loginUser) throws CannotDeleteException {
        if (!isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
        deleteQuestion();

        for (Answer answer : answers) {
            if (!answer.isOwner(loginUser)) {
                throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
            }
        }

        for (Answer answer : answers) {
            answer.delete();
        }
    }

    private void deleteQuestion() {
        deleted = true;
    }

    public List<DeleteHistory> deleteHistories() {
        if (answers == null) {
            return deletedQuestionHistories();
        }
        return Stream.concat(deletedQuestionHistories().stream(), deletedAnswerHistories().stream())
                .collect(Collectors.toList());
    }

    private List<DeleteHistory> deletedQuestionHistories() {
        if (!deleted()) {
            throw new NotFoundDeleteHistoryException();
        }
        return List.of(new DeleteHistory(ContentType.QUESTION, id(), writer, LocalDateTime.now()));
    }

    private List<DeleteHistory> deletedAnswerHistories() {
        for (Answer answer : answers) {
            validateDeleteAnswerStatus(answer);
        }
        return answers.stream()
                .map(Answer::deleteHistory)
                .collect(Collectors.toList());
    }

    private void validateDeleteAnswerStatus(Answer answer) {
        if (!answer.deleted()) {
            throw new NotFoundDeleteHistoryException();
        }
    }

    @Override
    public String toString() {
        return "Question [id=" + id() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
