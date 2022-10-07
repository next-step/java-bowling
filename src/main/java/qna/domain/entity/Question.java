package qna.domain.entity;

import qna.exception.CannotDeleteException;

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
    private Answers answers;

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

    public Question writeBy(User loginUser) {
        this.writer = loginUser;
        return this;
    }

    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        answers.add(answer);
    }

    public List<DeleteHistory> delete(User loginUser) throws CannotDeleteException {

        validateWriter(loginUser);

        this.delete();
        answers.delete(loginUser);

        List<DeleteHistory> deleteHistories = new ArrayList<>();

        deleteHistories.add(makeHistory());
        deleteHistories.addAll(answers.makeHistories());

        return deleteHistories;
    }

    public DeleteHistory makeHistory() {
        if(this.deleted == false){
            throw new CannotDeleteException("삭제 상태가 아닌 질문을 이력으로 만들 수 없습니다.");
        }
        return new DeleteHistory(ContentType.QUESTION, this.getId(), this.writer, LocalDateTime.now());
    }

    private void delete() {
        this.deleted = true;
    }

    private void validateWriter(User loginUser) throws CannotDeleteException {
        if(!loginUser.equals(this.writer)){
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }

    public boolean isDeleted() {
        return deleted;
    }
}
