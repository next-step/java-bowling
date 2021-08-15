package qna.domain.question;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import qna.domain.AbstractEntity;
import qna.domain.answer.Answer;
import qna.domain.answer.Answers;
import qna.domain.history.delete.DeleteHistory;
import qna.domain.user.User;
import qna.exception.CannotDeleteException;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Question extends AbstractEntity {
    @Column(length = 100, nullable = false)
    private String title;

    @Lob
    private String contents;

    @Getter
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
    private User writer;

    private Answers answers;

    private boolean deleted;

    public Question(final String title, final String contents) {
        this.title = title;
        this.contents = contents;
        this.answers = Answers.newInstance();
    }

    public Question(final long id, final String title, final String contents) {
        super(id);
        this.title = title;
        this.contents = contents;
        this.answers = Answers.newInstance();
    }

    public List<DeleteHistory> delete(final User loginUser) {
        return Stream.of(deleteQuestionBy(loginUser), answers.deleteBy(loginUser))
                .flatMap(Collection::stream)
                .collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
    }

    private List<DeleteHistory> deleteQuestionBy(final User loginUser) {
        verifyIsOwner(loginUser);
        this.deleted = true;
        return Collections.singletonList(DeleteHistory.ofQuestion(getId(), getWriter()));
    }

    private void verifyIsOwner(final User loginUser) {
        if (!isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
    }

    public Question writeBy(final User loginUser) {
        this.writer = loginUser;
        return this;
    }

    public void addAnswer(final Answer answer) {
        answer.toQuestion(this);
        this.answers.add(answer);
    }

    public boolean isOwner(final User loginUser) {
        return this.writer.equals(loginUser);
    }

    public boolean isDeleted() {
        return this.deleted;
    }

    public boolean isAnswersAllDeleted() {
        return answers.isAllDeleted();
    }
}
