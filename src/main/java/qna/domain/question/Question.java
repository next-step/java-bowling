package qna.domain.question;

import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.persistence.*;
import java.util.List;
import qna.domain.AbstractEntity;
import qna.domain.histroy.ContentType;
import qna.domain.histroy.DeleteHistory;
import qna.domain.user.User;

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
    private QuestionAnswers questionAnswers = new QuestionAnswers();

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
        questionAnswers.add(answer);
    }

    public boolean isDeleted() {
        return deleted;
    }

    public List<DeleteHistory> deleteBy(User requestUser) throws CannotDeleteException {
        if(!this.writer.equals(requestUser)){
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }

        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(createDeleteHistory());
        deleteHistories.addAll(this.questionAnswers.deleteAllBy(requestUser));

        this.deleted = true;
        return deleteHistories;
    }

    private DeleteHistory createDeleteHistory() {
        return new DeleteHistory(ContentType.QUESTION, this.getId(), this.writer, LocalDateTime.now());
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }
}
