package qna.domain.answer;

import qna.AnswerOtherWrittenException;
import qna.NotFoundException;
import qna.UnAuthorizedException;
import qna.domain.AbstractEntity;
import qna.domain.deleteHistory.DeleteHistory;
import qna.domain.question.Question;
import qna.domain.user.User;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Answer extends AbstractEntity {
    @ManyToOne(optional = false)
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_writer"))
    private User writer;

    @Embedded
    private AnswerBody answerBody;

    protected Answer() {
    }

    public Answer(User writer, Question question, String contents) {
        this(null, writer, question, contents);
    }

    public Answer(Long id, User writer, Question question, String contents) {
        super(id);

        if (writer == null) {
            throw new UnAuthorizedException();
        }

        if (question == null) {
            throw new NotFoundException();
        }

        this.writer = writer;
        this.answerBody = new AnswerBody(question, contents);
    }

    public User getWriter() {
        return writer;
    }

    public void toQuestion(Question question) {
        answerBody.toQuestion(question);
    }

    public DeleteHistory delete(User user) {
        checkAnswerWrittenByOthers(user);

        setDelete();

        return DeleteHistory.ofAnswer(this.id, user);
    }

    /**
     * 다른사람이 쓴 답변인지 확인합니다.
     *
     * @param user 확인할 유저
     * @throws AnswerOtherWrittenException 다른사람이 쓴 답변일경우
     */
    private void checkAnswerWrittenByOthers(User user) {
        if (!isOwner(user)) {
            throw new AnswerOtherWrittenException();
        }
    }

    private boolean isOwner(User writer) {
        return this.writer.equals(writer);
    }

    @Override
    public String toString() {
        return "Answer{" +
                "writer=" + writer +
                ", answerBody=" + answerBody +
                ", deleted=" + deleted +
                '}';
    }
}
