package qna.domain;

import java.time.LocalDateTime;
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

    public DeleteHistories delete(User loginUser) throws CannotDeleteException {
        hasQuestionDeleteAuthority(loginUser);
        hasAnswerDeleteAuthority(loginUser);

        DeleteHistories deleteHistories = new DeleteHistories();
        deleteHistories.add(deleteQuestion());
        deleteHistories.add(deleteAnswers());

        return deleteHistories;
    }

    private void hasQuestionDeleteAuthority(User loginUser) throws CannotDeleteException {
        if (!isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
    }

    private void hasAnswerDeleteAuthority(User loginUser) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.hasDeleteAuthority(loginUser);
        }
    }

    private DeleteHistory deleteQuestion() {
        deleted = true;
        return new DeleteHistory(ContentType.QUESTION, super.getId(), writer, LocalDateTime.now());
    }

    private List<DeleteHistory> deleteAnswers() {
        List<DeleteHistory> deleteHistories = new ArrayList<>();

        for (Answer answer : answers) {
            deleteHistories.add(answer.delete());
        }

        return deleteHistories;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
