package qna.domain;

import org.hibernate.annotations.Where;
import qna.CannotDeleteException;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toCollection;

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

    @Column
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

    public Question beDeletedBy(User owner) {
        checkIfUserIsReallyOwner(owner);
        setDeleted();

        answers.forEach(answer -> answer.setDeleted(true));

        return this;
    }

    private void checkIfUserIsReallyOwner(User owner) {
        if (!isOwner(owner)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }

        for (Answer answer : getAnswers()) {
            if (!answer.isOwner(owner)) {
                throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
            }
        }
    }

    private void setDeleted() {
        this.deleted = true;
    }

    public List<DeleteHistory> createDeleteHistories() {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(makeDeleteHistoryOfQuestion());

        return getAnswers().stream()
                           .map(Answer::createAnswerDeleteHistory)
                           .collect(toCollection(() -> deleteHistories));
    }

    private DeleteHistory makeDeleteHistoryOfQuestion() {
        if (!isDeleted()) {
            throw new IllegalStateException("질문이 삭제된 상태여야 히스토리를 생성할 수 있습니다.");
        }

        return new DeleteHistory(ContentType.QUESTION, getId(), this.writer, LocalDateTime.now());
    }

}
