package qna.domain;

import java.time.LocalDateTime;

import qna.CannotDeleteException;
import qna.NotFoundException;
import qna.UnAuthorizedException;

import javax.persistence.*;

import static qna.domain.DeleteHistory.newDeleteHistory;

@Entity
public class Answer extends AbstractEntity {

    @ManyToOne(optional = false)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_writer"))
    private User writer;

    @ManyToOne(optional = false)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_to_question"))
    private Question question;

    @Lob
    private String contents;

    private boolean deleted = false;

    public static Answer newAnswer(User writer,Question question,String contents){
        if (writer == null) {
            throw new UnAuthorizedException();
        }

        if (question == null) {
            throw new NotFoundException();
        }

        return new Answer(writer,question,contents);
    }

    public static Answer newAnswerWithDeleted(User writer, Question question, String contents,boolean status){
        return new Answer(writer,question,contents,status);
    }

    private Answer(User writer, Question question, String contents) {
        this(null, writer, question, contents);
    }

    private Answer(User writer, Question question, String contents,boolean status) {
        this(writer,question,contents);
        this.deleted = status;
    }

    private Answer(Long id, User writer, Question question, String contents) {
        super(id);

        this.writer = writer;
        this.question = question;
        this.contents = contents;
    }

    private void validWriter(User writer) throws CannotDeleteException {
        if (writer != this.writer) {
            throw new CannotDeleteException("답변자와 질문자가 같지 않습니다");
        }
    }

    public void toQuestion(Question question) {
        this.question = question;
    }

    public void delete(User writer) throws CannotDeleteException {
        validWriter(writer);
        this.deleted = true;
    }

    public DeleteHistory answerHistory() {
        return newDeleteHistory(ContentType.ANSWER, this.Id(), writer, LocalDateTime.now());
    }

    public User getWriter() {
        return writer;
    }

    @Override
    public String toString() {
        return "Answer [id=" + Id() + ", writer=" + writer + ", contents=" + contents + "]";
    }

}
