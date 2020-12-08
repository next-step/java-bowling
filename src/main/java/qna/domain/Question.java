package qna.domain;

import qna.exception.CannotDeleteException;

import javax.persistence.*;
import java.time.LocalDateTime;

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


    public Question delete(User loginUser, DeleteHistories deleteHistories) throws CannotDeleteException {
        if (loginUser != writer) {
            throw new CannotDeleteException("질문자와 답변자가 달라서 삭제할수 없습니다.");
        }
        if (isAnswersOwner()) {
            throw new CannotDeleteException("질문의 작성자가 아닌 유저가 답변을 달았을경우 삭제가 불가능합니다.");
        }

        deleteQuestion(deleteHistories);
        deleteAnswer(loginUser, deleteHistories);
        return this;
    }

    private void deleteQuestion(DeleteHistories deleteHistories) {
        deleteHistories.save(new DeleteHistory(ContentType.QUESTION, getId(), writer, LocalDateTime.now()));
        this.deleted = true;
    }

    private void deleteAnswer(User loginUser, DeleteHistories deleteHistories) {
        answers.deleteAnswers(loginUser, deleteHistories);

    }

    private boolean isAnswersOwner() {
        return answers.getAnswers().stream()
                .anyMatch(answer -> !answer.isOwner(writer));
    }

    public boolean isDeleted() {
        return deleted;
    }


    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
