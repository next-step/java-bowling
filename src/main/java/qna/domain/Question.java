package qna.domain;

import org.hibernate.annotations.Where;
import qna.CannotDeleteException;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    private final List<Answer> answers = new ArrayList<>();

    private boolean deleted = false;

    public Question() {
    }

    private Question(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    private Question(long id, String title, String contents) {
        super(id);
        this.title = title;
        this.contents = contents;
    }

    public static Question of(long id, String title, String contents) {
        return new Question(id, title, contents);
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
        return this.writer.equals(loginUser);
    }


    public boolean isDeleted() {
        return deleted;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public List<DeleteHistory> deleted(User loginUser) throws CannotDeleteException {
        validateWriter(loginUser);
        this.deleted = true;

        return deletedHistories(this.getId());
    }

    private void validateWriter(User loginUser) throws CannotDeleteException {
        if (!isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
    }

    private List<DeleteHistory> deletedHistories(long questionId) throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(DeleteHistory.of(ContentType.QUESTION, questionId, this.writer));

        deleteAnswers(deleteHistories);

        return deleteHistories;
    }

    private void deleteAnswers(List<DeleteHistory> deleteHistories) throws CannotDeleteException {
        for (Answer answer : this.answers) {
            deleteHistories.add(answer.deleted(this.writer));
        }
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
