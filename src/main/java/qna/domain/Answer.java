package qna.domain;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import qna.CannotDeleteException;
import qna.NotFoundException;
import qna.UnAuthorizedException;

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

        if(writer == null) {
            throw new UnAuthorizedException();
        }

        if(question == null) {
            throw new NotFoundException();
        }

		this.writer = writer;
		this.question = question;
		this.contents = contents;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public DeleteHistory delete(User loginUser) throws CannotDeleteException {
		Objects.requireNonNull(loginUser);
		validateWriter(loginUser);
		this.deleted = true;
		return new DeleteHistory(ContentType.ANSWER, getId(), writer, LocalDateTime.now());
	}

	private void validateWriter(User loginUser) throws CannotDeleteException {
		if (!writer.equals(loginUser)) {
			throw new CannotDeleteException("다른 사람이 쓴 답변이라 삭제할 수 없습니다.");
		}
	}

	public User getWriter() {
		return writer;
	}

	public void toQuestion(Question question) {
		this.question = question;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		if (!super.equals(o))
			return false;
		Answer answer = (Answer)o;
		return deleted == answer.deleted && Objects.equals(writer, answer.writer) && Objects.equals(
			question, answer.question) && Objects.equals(contents, answer.contents);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), writer, question, contents, deleted);
	}

	@Override
	public String toString() {
		return "Answer [id=" + getId() + ", writer=" + writer + ", contents=" + contents + "]";
	}
}
