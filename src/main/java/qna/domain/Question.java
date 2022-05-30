package qna.domain;

import qna.CannotDeleteException;

import javax.persistence.*;
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

    @Embedded
    private final Answers answers = Answers.getInstance();

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

    public Answers getAnswers() {
        return answers;
    }

    public List<DeleteHistory> deleted(User loginUser) throws CannotDeleteException {
        validateWriter(loginUser);

        List<DeleteHistory> deleteHistories = new ArrayList<>();

        this.deleted = true;
        deleteHistories.add(deleted());

        List<DeleteHistory> answersDeleteHistories = this.answers.deleted(this.writer);

        return Stream.concat(deleteHistories.stream(), answersDeleteHistories.stream()).collect(Collectors.toList());
    }

    public DeleteHistory deleted() {
        return DeleteHistory.of(ContentType.QUESTION, getId(), this.writer);
    }

    private void validateWriter(User loginUser) throws CannotDeleteException {
        if (!isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
