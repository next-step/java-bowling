package qna.domain;

import qna.NotFoundException;

import javax.persistence.*;

@Embeddable
public class AnswerBody {
    @ManyToOne(optional = false)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_to_question"))
    private Question question;

    @Lob
    private String contents;

    public AnswerBody() {
    }

    public AnswerBody(Question question, String contents) {
        if (question == null) {
            throw new NotFoundException();
        }
        this.question = question;
        this.contents = contents;
    }

    public void toQuestion(Question question) {
        this.question = question;
    }
}
