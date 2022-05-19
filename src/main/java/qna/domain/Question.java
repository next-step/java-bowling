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

    public boolean isOwner(User loginUser) {
        return writer.equals(loginUser);
    }


    public List<DeleteHistory> addAllDeleteHistory(User loginUser){
        changeDeleteState(true);
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, this.getId(), this.writer, LocalDateTime.now()));
        answers.answersDelete(loginUser, deleteHistories);

        return deleteHistories;
    }


    public List<DeleteHistory> delete(User loginUser) throws CannotDeleteException {
        if(!isOwner(loginUser)){
            throw new CannotDeleteException(loginUser + "는 질문 작성자가 아니기 때문에 삭제할 권한이 없습니다.");
        }

        if(answers.canDeletedAnswerCondition(loginUser)){
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
        return addAllDeleteHistory(loginUser);

    }

    private void changeDeleteState(boolean state){
        this.deleted = state;

    }

    public boolean isDeleted() {
        return deleted;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
