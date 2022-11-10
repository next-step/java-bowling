package qna.domain;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Question extends AbstractContentsDeletableEntity {
    @Column(length = 100, nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
    private User writer;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private List<Answer> answers = new ArrayList<>();

    public Question() {
    }

    public Question(String title, String contents) {
        super(contents);
        this.title = title;
    }

    public Question(long id, String title, String contents) {
        super(id, contents);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public Question setTitle(String title) {
        this.title = title;
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

    public boolean isAllAnswersOwner(User user) {
        for (Answer answer : answers) {
            if (!answer.isOwner(user)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public DeleteHistory delete() {
        super.delete();
        return new DeleteHistory(ContentType.QUESTION, getId(), writer, LocalDateTime.now());
    }

    public List<DeleteHistory> deleteAnswers() {
        return answers.stream()
                .map(Answer::delete)
                .collect(Collectors.toList());
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", contents=" + getContents() + ", writer=" + writer + "]";
    }
}
