package qna.domain;

import org.hibernate.annotations.Where;
import qna.CannotDeleteException;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    public void delete(User loginUser) {
        validateQuestionAuth(loginUser);
        validateAnswerAuth(loginUser);
        this.deleted = true;
        answers.forEach(Answer::delete);
    }

    public List<DeleteHistory> deleteHistories() {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        List<Optional<DeleteHistory>> answerHistories = answers.stream()
                .map(Answer::deleteHistory)
                .collect(Collectors.toList());

        boolean anyDeleteHistoryIsEmpty = answerHistories.stream().anyMatch(Optional::isEmpty);
        if(!isDeleted() || anyDeleteHistoryIsEmpty) {
            return deleteHistories;
        }

        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, getId(), writer, LocalDateTime.now()));
        answerHistories.forEach(history -> deleteHistories.add(history.get()));
        return deleteHistories;
    }

    private void validateQuestionAuth(User loginUser) {
        if (!this.isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
    }

    private void validateAnswerAuth(User loginUser) {
        Optional<Answer> notAuthAnswer = answers.stream().filter(answer -> !answer.isOwner(loginUser)).findAny();
        if (notAuthAnswer.isPresent()) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }


    public boolean isDeleted() {
        return deleted;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
