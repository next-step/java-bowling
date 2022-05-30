package qna.domain.answer;

import qna.domain.question.Question;
import qna.domain.user.User;

import javax.persistence.*;

@Embeddable
public class AnswerInfo {
    @ManyToOne(optional = false)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_writer"))
    private User writer;
    @ManyToOne(optional = false)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_to_question"))
    private Question question;

    @Lob
    private String contents;

    public AnswerInfo() {
    }

    public AnswerInfo(User writer, Question question, String contents) {
        this.writer = writer;
        this.question = question;
        this.contents = contents;
    }

    public User writer() {
        return this.writer;
    }

    public Question question() {
        return this.question;
    }

    public String contents() {
        return contents;
    }

    public void toQuestion(Question question) {
        this.question = question;
    }

    protected boolean isWriter(User writer) {
        return this.writer.equals(writer);
    }

    @Override
    public String toString() {
        return "AnswerBody{" +
                "writer=" + writer +
                ", question=" + question +
                ", contents='" + contents + '\'' +
                '}';
    }
}
