package qna.domain;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Question extends AbstractEntity {
    @Column(length = 100, nullable = false)
    private String title;

    @Embedded
    @AssociationOverride(name = "writer", joinColumns = @JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer")))
    private PostInfo postInfo;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private List<Answer> answers = new ArrayList<>();

    public Question() {
    }

    public Question(String title, String contents) {
        this(null, title, contents);
    }

    public Question(Long id, String title, String contents) {
        super(id);
        this.title = title;
        postInfo = new PostInfo(contents);
    }

    public String getTitle() {
        return title;
    }

    public Question setTitle(String title) {
        this.title = title;
        return this;
    }

    public User getWriter() {
        return postInfo.getWriter();
    }

    public Question writeBy(User loginUser) {
        postInfo.writeBy(loginUser);
        return this;
    }

    public void addAnswer(Answer answer) {
        answer.toQuestion(this);
        answers.add(answer);
    }

    public boolean isOwner(User loginUser) {
        return postInfo.isOwner(loginUser);
    }

    public Question setDeleted(boolean deleted) {
        postInfo.delete();
        return this;
    }

    public boolean isDeleted() {
        return postInfo.isDeleted();
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    @Override
    public String toString() {
        return "Question [id=" + getId() + ", title=" + title + ", postInfo=" + postInfo + "]";
    }
}
