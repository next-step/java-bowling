package qna.domain;

import java.time.LocalDateTime;
import java.util.Optional;

import qna.CannotDeleteException;
import qna.NotFoundException;
import qna.UnAuthorizedException;

import javax.persistence.*;

@Entity
public class Answer extends AbstractEntity {
	public static final String NO_PERMISSION = "다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.";

	@Embedded
	private AnswerContext answerContext;

	@ManyToOne(optional = false)
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_to_question"))
	private Question question;

	public Answer() {
	}

	public Answer(User writer, Question question, String contents) {
		this(null, writer, question, contents);
	}

	public Answer(Long id, User writer, Question question, String contents) {
		super(id);

		if (question == null) {
			throw new NotFoundException();
		}

		this.question = question;
		this.answerContext = new AnswerContext(writer, contents);
	}

	public Answer setDeleted(boolean deleted) {
		this.answerContext.setDeleted(deleted);
		return this;
	}

	public boolean isDeleted() {
		return this.answerContext.isDeleted();
	}

	public boolean isOwner(User writer) {
		return this.answerContext.isOwner(writer);
	}

	public User getWriter() {
		return this.answerContext.getWriter();
	}

	public String getContents() {
		return this.answerContext.getContents();
	}

	public void toQuestion(Question question) {
		this.question = question;
	}

	public DeleteHistory delete(User loginUser) throws CannotDeleteException {
		if (this.isOwner(loginUser)) {
			this.setDeleted(true);
			return new DeleteHistory(ContentType.ANSWER, this.getId(), this.getWriter(), LocalDateTime.now());
		}
		throw new CannotDeleteException(NO_PERMISSION);
	}

	@Override
	public String toString() {
		return "Answer [id=" + getId() + ", writer=" + this.answerContext.getWriter() + ", contents="
			+ this.answerContext.getContents() + "]";
	}
}
