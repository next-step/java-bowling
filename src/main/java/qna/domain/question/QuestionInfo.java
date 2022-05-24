package qna.domain.question;

import qna.domain.user.User;

import javax.persistence.*;

@Embeddable
public class QuestionInfo {
    @Column(length = 100, nullable = false)
    private String title;

    @Lob
    private String contents;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
    private User writer;

    public QuestionInfo() {
    }

    public QuestionInfo(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public User writer() {
        return this.writer;
    }

    protected void writeBy(User writer) {
        this.writer = writer;
    }

    @Override
    public String toString() {
        return "QuestionInfo{" +
                "title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", writer=" + writer +
                '}';
    }
}
