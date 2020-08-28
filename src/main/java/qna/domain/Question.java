package qna.domain;

import org.hibernate.annotations.Where;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;

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

    public boolean isOwner(User loginUser) {
        return writer.equals(loginUser);
    }

    private void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    private Question deletedQuestion() {
        setDeleted(true);
        return this;
    }

    private void deletedAllAnswer(){
        getAnswers().deletedAllAnswer();
    }

    public boolean isDeleted() {
        return deleted;
    }

    private Answers getAnswers() {
        return Answers.of(answers);
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }

    public void verifyOwner(User loginUser) throws CannotDeleteException {
        if (!isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
    }

    public void verifyOwnerForAnswers(User longinUser) throws CannotDeleteException {
       getAnswers().verifyAllOwner(longinUser);
    }

    public List<DeleteHistory> toDeleteHistories() {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(toDeleteHistory());
        deleteHistories.addAll(getAnswers().toDeleteHistories());
        return deleteHistories;
    }

    private DeleteHistory toDeleteHistory() {
        return new DeleteHistory(ContentType.QUESTION, getId(), getWriter(), LocalDateTime.now());
    }

    public void deleteQna(User loginUser, BiConsumer<Question, List<Answer>> updateQna) throws CannotDeleteException {
        verifyOwner(loginUser);
        verifyOwnerForAnswers(loginUser);
        deletedQuestion().deletedAllAnswer();
        updateQna.accept(this, Collections.unmodifiableList(answers));
    }
}
