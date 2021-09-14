package qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import qna.exception.NotQuestionWriterException;
import qna.exception.OtherUserAnswerFoundException;

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

    public Answers getAnswers() {
        return answers;
    }

    public void validateDelete(User loginUser)
        throws NotQuestionWriterException, OtherUserAnswerFoundException {

        if (!isOwner(loginUser)) {
            throw new NotQuestionWriterException("질문을 삭제할 권한이 없습니다.");
        }

        Answers answers = getAnswers();

        if(!answers.isDeletable(loginUser)) {
            throw new OtherUserAnswerFoundException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    public List<DeleteHistory> delete(User loginUser) throws NotQuestionWriterException, OtherUserAnswerFoundException {
        validateDelete(loginUser);

        setDeleted(true);
        List<DeleteHistory> questionDeleteHistories = createDeleteHistory();
        List<DeleteHistory> answersDeleteHistories = answers.deleteAll();

        return Stream.of(questionDeleteHistories, answersDeleteHistories)
            .flatMap(h -> h.stream())
            .collect(Collectors.toList());
    }

    private List<DeleteHistory> createDeleteHistory() {
        List<DeleteHistory> questionDeleteHistories = new ArrayList<>();
        questionDeleteHistories.add(new DeleteHistory(ContentType.QUESTION, getId(), getWriter(), LocalDateTime.now()));
        return questionDeleteHistories;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
