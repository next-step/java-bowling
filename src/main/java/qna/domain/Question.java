package qna.domain;

import qna.CannotDeleteException;
import qna.exception.ErrorMessage;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
    private Answers answers = new Answers();

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

    public String getContents() {
        return contents;
    }

    public User getWriter() {
        return writer;
    }

    public Question writeBy(User loginUser) {
        this.writer = loginUser;
        return this;
    }

    public void addAnswer(final Answer answer) {
        answer.toQuestion(this);
        answers.add(answer);
    }

    public void verifyOwner(final User loginUser) {
        if (!writer.isSame(loginUser)) {
            throw new CannotDeleteException(ErrorMessage.NO_PERMISSION_TO_DELETE_QUESTION);
        }
    }

    public List<DeleteHistory> delete(final User user) {
        verifyOwner(user);

        return Stream.of(deleteQuestion(), deleteAnswers(user))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private List<DeleteHistory> deleteQuestion() {
        this.deleted = true;

        DeleteHistory deleteHistory = new DeleteHistory.Builder(ContentType.QUESTION, getId())
                .deletedBy(writer)
                .createDate(LocalDateTime.now())
                .build();
        return Collections.singletonList(deleteHistory);
    }

    private List<DeleteHistory> deleteAnswers(final User user) {
        return answers.delete(user);
    }

    public boolean isDeleted() {
        return deleted;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
