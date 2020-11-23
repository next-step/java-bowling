package qna.domain;

import org.hibernate.annotations.Where;
import qna.exceptions.CannotDeleteException;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Question extends AbstractEntity {
    public static final String ERROR_DENIED_DELETE_PERMISSION = "질문을 삭제할 권한이 없습니다.";
    public static final String ERROR_EXISTS_OTHER_WRITTEN = "다른 사람이 쓴 답변이 있어 삭제할 수 없습니다";

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

    public Question deleteQuestion() {
        if (!this.deleted) {
            this.deleted = true;
        }
        return this;
    }

    public Question resurrectionQuestion() {
        if (this.deleted) {
            this.deleted = false;
        }
        return this;
    }

    public Question setDeleted(boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public Answers getAnswers() {
        return Answers.from(answers);
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }

    public DeleteHistory makeDeleteHistory() {
        return DeleteHistory.Builder()
                .contentType(ContentType.QUESTION)
                .contentId(super.getId())
                .deletedBy(writer)
                .createDate(LocalDateTime.now())
                .build();
    }

    public DeleteHistories delete(User user) throws CannotDeleteException {
        isValidDeletePermission(user);
        isValidDelete(user);

        deleteQuestion();
        List<DeleteHistory> histories = getAnswers().deleteAll();
        return DeleteHistories.of(makeDeleteHistory(), histories);
    }

    private void isValidDeletePermission(User loginUser) throws CannotDeleteException {
        if (!isOwner(loginUser)) {
            throw new CannotDeleteException(ERROR_DENIED_DELETE_PERMISSION);
        }
    }
    private void isValidDelete(User user) throws CannotDeleteException {
        if (!answers.isEmpty() && answers.stream()
                .anyMatch(answer -> !answer.isOwner(user))) {
            throw new CannotDeleteException(ERROR_EXISTS_OTHER_WRITTEN);
        }
    }
}
