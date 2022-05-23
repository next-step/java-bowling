package qna.domain;

import org.hibernate.annotations.Where;
import qna.CannotDeleteException;
import qna.NotFoundDeleteHistoryException;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    @Embedded
    private Answers answers;

    private boolean deleted = false;

    public Question() {
    }

    public Question(long id, String title, String contents) {
        this(id, title, contents, new Answers(new ArrayList<>()));
    }

    public Question(long id, String title, String contents, Answers answers) {
        super(id);
        this.title = title;
        this.contents = contents;
        this.answers = answers;
    }

    public Question writeBy(User loginUser) {
        this.writer = loginUser;
        return this;
    }

    public boolean isOwner(User loginUser) {
        return writer.equals(loginUser);
    }

    public boolean deleted() {
        return deleted;
    }

    public void delete(User loginUser) throws CannotDeleteException {
        if (!isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
        deleteQuestion();

        if (answers != null) {
            answers.deleteAll(loginUser);
        }
    }

    private void deleteQuestion() {
        deleted = true;
    }

    public List<DeleteHistory> deleteHistories() {
        if (answers == null) {
            return deletedQuestionHistories();
        }
        return Stream.concat(deletedQuestionHistories().stream(), answers.deletedAnswerHistories().stream())
                .collect(Collectors.toList());
    }

    private List<DeleteHistory> deletedQuestionHistories() {
        if (!deleted()) {
            throw new NotFoundDeleteHistoryException();
        }
        return List.of(new DeleteHistory(ContentType.QUESTION, id(), writer, LocalDateTime.now()));
    }

    @Override
    public String toString() {
        return "Question [id=" + id() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
