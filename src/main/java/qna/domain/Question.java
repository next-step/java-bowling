package qna.domain;

import qna.CannotDeleteException;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.TRUE;

@Entity
public class Question extends AbstractEntity {

    @Column(length = 100, nullable = false)
    private String title;

    @Lob
    private String contents;

    @Embedded
    private final Answers answers = new Answers();

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
    private User writer;

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

    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        answers.add(answer);
    }

    public boolean isOwner(User loginUser) {
        return writer.equals(loginUser);
    }

    public List<DeleteHistory> deleteQuestion(User loginUser){
        List<DeleteHistory> deleteList = new ArrayList<>();

        checkAuthorization(loginUser);
        deleteList.add(deleteQuestion());
        deleteList.addAll(answers.deleteAll(loginUser));

        return deleteList;
    }

    protected DeleteHistory deleteQuestion() {
        this.deleted = TRUE;
        return new DeleteHistory(ContentType.QUESTION, getId(), getWriter(), LocalDateTime.now());
    }

    public boolean isDeleted() {
        return deleted;
    }

    public List<Answer> answersToQuestion() {
        return answers.answers();
    }

    private void checkAuthorization(User loginUser) {
        if (!isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s", getId(), title, contents, writer);
    }
}
