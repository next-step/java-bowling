package qna.domain;

import qna.exception.CannotDeleteException;
import qna.exception.NotOwnAnswersException;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

@Entity
public class Question extends AbstractEntity {
    @Column(length = 100, nullable = false)
    private String title;

    @Lob
    private String contents;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
    private User writer;

    private Answers answers = new Answers();

    private boolean deleted = false;

    protected Question() {
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


    public User getWriter() {
        return writer;
    }

    public Question writeBy(User loginUser) {
        this.writer = loginUser;
        return this;
    }

    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        answers.addAnswer(answer);
    }


    public DeleteHistories delete(User loginUser) throws CannotDeleteException {
        validLoginUserNotMatchWriter(loginUser);
        validNotOwnAnswers();


        return Stream.concat(
                Stream.of(deleteQuestion()),
                deleteAnswers(loginUser).stream())
                .collect(collectingAndThen(toList(), DeleteHistories::of));
    }

    private void validNotOwnAnswers() throws CannotDeleteException {
        if (isAnswersOwner()) {
            throw new NotOwnAnswersException();
        }
    }

    private void validLoginUserNotMatchWriter(User loginUser) throws CannotDeleteException {
        if (loginUser != writer) {
            throw new CannotDeleteException("질문자와 답변자가 달라서 삭제할수 없습니다.");
        }
    }

    private DeleteHistory deleteQuestion() {
        this.deleted = true;
        return new DeleteHistory(ContentType.QUESTION, getId(), getWriter(), LocalDateTime.now());
    }

    private List<DeleteHistory> deleteAnswers(final User loginUser) throws CannotDeleteException {
        return answers.deleteAnswers(loginUser);
    }

    private boolean isAnswersOwner() {
        return answers.contains(writer);
    }

    public boolean isDeleted() {
        return deleted;
    }


    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
