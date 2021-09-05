package qna.domain;

import qna.CannotDeleteException;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
public class Question extends AbstractEntity {

    private static String NO_DELETION_AUTHORITY = "질문을 삭제할 권한이 없습니다.";

    @Column(length = 100, nullable = false)
    private String title;

    @Lob
    private String contents;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
    private User writer;

    @Embedded
    private Answers answers;

    private boolean deleted = false;

    protected Question() {
    }

    public Question(final long id, final String title, final String contents) {
        super(id);
        this.title = title;
        this.contents = contents;
        this.answers = new Answers();
    }

    public Question writeBy(final User loginUser) {
        this.writer = loginUser;
        return this;
    }

    public void addAnswer(final Answer answer) {
        answer.toQuestion(this);
        answers.addAnswer(answer);
    }

    public List<DeleteHistory> delete(final User loginUser) throws CannotDeleteException {
        return Stream.of(deleteQuestion(loginUser), answers.delete(loginUser))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private List<DeleteHistory> deleteQuestion(final User loginUser) {
        validateOwner(loginUser);
        deleted = true;
        return Collections.singletonList(
                DeleteHistory.questionOf(getId(), writer));
    }

    private void validateOwner(final User loginUser) throws CannotDeleteException {
        if (!writer.equals(loginUser)) {
            throw new CannotDeleteException(NO_DELETION_AUTHORITY);
        }
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }

}
