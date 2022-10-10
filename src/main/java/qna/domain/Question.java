package qna.domain;

import qna.CannotDeleteException;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Question extends AbstractEntity {
    @Embedded
    private QuestionBody questionBody;
    
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
    private User writer;
    
    @Embedded
    private final Answers answers = new Answers();
    
    private boolean deleted = false;
    
    public Question() {
    }
    
    public Question(String title, String contents) {
        this.questionBody = new QuestionBody(title, contents);
    }
    
    public Question(long id, String title, String contents) {
        super(id);
        this.questionBody = new QuestionBody(title, contents);
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
    
    public List<DeleteHistory> delete(User loginUser) throws CannotDeleteException {
        if (!isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
        this.deleted = true;
        
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, getId(), writer, LocalDateTime.now()));
        deleteHistories.addAll(answers.delete(loginUser));
        return deleteHistories;
    }
    
    @Override
    public String toString() {
        return "Question{" +
                "questionBody=" + questionBody +
                ", writer=" + writer +
                ", answers=" + answers +
                ", deleted=" + deleted +
                '}';
    }
}
