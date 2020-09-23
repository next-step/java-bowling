package qna.domain;

import org.hibernate.annotations.Where;
import qna.CannotDeleteException;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Question extends AbstractEntity {
    private boolean deleted;

    @Column(length = 100, nullable = false)
    private String title;

    @Lob
    private String contents;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
    private User writer;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private Answers answers;

    public Question() {
    }

    public Question(String title, String contents, User writer) {
        this.title = title;
        this.contents = contents;
        this.answers = new Answers();
        this.deleted = false;
        this.writer = writer;
    }

    public Question(long id, String title, String contents, User writer) {
        super(id);
        this.title = title;
        this.contents = contents;
        this.answers = new Answers();
        this.deleted = false;
        this.writer = writer;
    }

    public void deleteBy(User loginUser) throws CannotDeleteException {
        if (loginUser != this.writer) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
            this.deleted(true);
            DeleteHistories deleteHistories = new DeleteHistories();
//            deleteHistories.add(new DeleteHistory(ContentType.QUESTION, questionId, question.getWriter(), LocalDateTime.now()));
//            deleteHistoryService.save(deleteHistory);
    }

    public List<Answer> getAnswers() {
        return answers.getAnswers();
    }

    public User getWriter() {
        return writer;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public boolean isDeleted() {
        return deleted;
    }


    public Question writeBy(User loginUser) {
        this.writer = loginUser;
        return this;
    }

    public boolean deleted(boolean bool) {
        this.deleted = bool;
        return deleted;
    }

    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        answers.add(answer);
    }

    public boolean isOwner(User loginUser) {
        return writer.equals(loginUser);
    }


    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
    }

}
