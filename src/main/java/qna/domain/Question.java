package qna.domain;

import qna.CannotDeleteException;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Question extends AbstractEntity {

    public static final String GUIDE_ERR_AUTHORIZATION = "질문을 삭제할 권한이 없습니다.";

    @Embedded
    private QuestionContents questionContents;

    @Embedded
    private final Answers answers = new Answers();

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
    private User writer;

    @Embedded
    private final Deleted deleted = new Deleted();

    public Question() {
    }

    public Question(String title, String contents) {
        this(0L, title, contents);
    }

    public Question(long id, String title, String contents) {
        super(id);
        this.questionContents = new QuestionContents(title, contents);
    }

    public User writer() {
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

    public Question update(QuestionContents questionContents) {
        this.questionContents = questionContents;
        return this;
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
        deleted.delete();
        return new DeleteHistory(ContentType.QUESTION, getId(), writer(), LocalDateTime.now());
    }

    public boolean isDeleted() {
        return deleted.isDelete();
    }

    public List<Answer> answersToQuestion() {
        return answers.answers();
    }

    private void checkAuthorization(User loginUser) {
        if (!isOwner(loginUser)) {
            throw new CannotDeleteException(GUIDE_ERR_AUTHORIZATION);
        }
    }

    public String contentTitle() {
        return questionContents.getTitle();
    }

    public String content() {
        return questionContents.getContents();
    }

    @Override
    public String toString() {
        return String.format("%s, %s,  %s", getId(), questionContents, writer);
    }
}
