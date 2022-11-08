package qna.domain;

import java.time.LocalDateTime;
import qna.CannotDeleteException;
import qna.NotFoundException;
import qna.UnAuthorizedException;

import javax.persistence.*;

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

	public Answer() {
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
		this.question = question;
		this.contents = contents;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void validWriter(User writer) throws CannotDeleteException {
		if (writer != this.writer) {
			throw new CannotDeleteException("답변자와 질문자가 같지 않습니다");
		}
	}

	public User getWriter() {
		return writer;
	}

	public void toQuestion(Question question) {
		this.question = question;
	}

	public void delete(User writer) throws CannotDeleteException {
		validWriter(writer);
		this.deleted = true;
	}

	public DeleteHistory answerHistory() {
		return new DeleteHistory(ContentType.ANSWER, this.Id(), writer, LocalDateTime.now());
	}

	@Override
	public String toString() {
		return "Answer [id=" + Id() + ", writer=" + writer + ", contents=" + contents + "]";
	}

}
