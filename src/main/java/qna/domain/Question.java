package qna.domain;

import qna.CannotDeleteException;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    public List<DeleteHistory> deleteBy(User user) {
        if (!isOwner(user)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }

        List<DeleteHistory> answerDeleteHistories = deleteAnswersBy(user);

        DeleteHistory questionDeleteHistory = new DeleteHistory(ContentType.QUESTION, id, writer, LocalDateTime.now());
        this.deleted = true;

        return mergeQuestionAndAnswerDeleteHistories(questionDeleteHistory, answerDeleteHistories);
    }

    private List<DeleteHistory> deleteAnswersBy(User user) {
        try {
            return answers.deleteBy(user);
        } catch (CannotDeleteException e) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    private List<DeleteHistory> mergeQuestionAndAnswerDeleteHistories(DeleteHistory questionDeleteHistory, List<DeleteHistory> answerDeleteHistories) {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(questionDeleteHistory);
        deleteHistories.addAll(answerDeleteHistories);
        return deleteHistories;
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

    public boolean isDeleted() {
        return deleted;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
