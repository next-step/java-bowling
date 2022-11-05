package qna.domain;

import java.time.LocalDateTime;
import java.util.stream.Collectors;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import qna.CannotDeleteException;

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

    public List<DeleteHistory> delete(User user) throws CannotDeleteException {
        validateWriter(user);

        setDeleted(true);

        List<DeleteHistory> answersDeleteHistories = deleteAllAnswers(user);

        return createDeleteHistories(answersDeleteHistories);
    }

    private void validateWriter(User user) throws CannotDeleteException {
        if (!isOwner(user)) {
            throw new CannotDeleteException("질문은 질문자만 지울 수 있습니다.");
        }
    }

    public boolean isOwner(User loginUser) {
        return writer.equals(loginUser);
    }

    public Question setDeleted(boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    private List<DeleteHistory> createDeleteHistories(List<DeleteHistory> answersDeleteHistories) {
        List<DeleteHistory> deleteHistories = new ArrayList<>();

        deleteHistories.add(createQuestionDeleteHistory());

        deleteHistories.addAll(answersDeleteHistories);

        return deleteHistories;
    }

    private DeleteHistory createQuestionDeleteHistory() {
        return new DeleteHistory(ContentType.QUESTION, this.getId(), this.writer, LocalDateTime.now());
    }

    public List<DeleteHistory> deleteAllAnswers(User user) throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();

        for (Answer answer : this.answers) {
            deleteHistories.add(answer.delete(user));
        }

        return deleteHistories;
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
