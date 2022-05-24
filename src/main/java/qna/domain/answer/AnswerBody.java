package qna.domain.answer;

import qna.domain.question.Question;

import javax.persistence.Embeddable;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Embeddable
public class AnswerBody {
    @ManyToOne(optional = false)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_to_question"))
    private Question question;

    @Lob
    private String contents;

    protected AnswerBody() {
    }

    public AnswerBody(Question question, String contents) {
        this.question = question;
        this.contents = contents;
    }

    public void toQuestion(Question question) {
        this.question = question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AnswerBody that = (AnswerBody) o;
        return Objects.equals(question, that.question) && Objects.equals(contents, that.contents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(question, contents);
    }

    @Override
    public String toString() {
        return "AnswerBody{" +
                "question=" + question +
                ", contents='" + contents + '\'' +
                '}';
    }
}
