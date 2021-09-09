package qna.domain;

import javax.persistence.Embeddable;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import qna.UnAuthorizedException;

@Embeddable
public class AnswerContext {
	@ManyToOne(optional = false)
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_writer"))
	private User writer;

	@Lob
	private String contents;

	private boolean deleted = false;

	public AnswerContext(User writer, String contents) {
		if(writer == null) {
			throw new UnAuthorizedException();
		}
		this.writer = writer;
		this.contents = contents;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public boolean isDeleted() {
		return this.deleted;
	}

	public boolean isOwner(User writer) {
		return this.writer.equals(writer);
	}

	public User getWriter() {
		return this.writer;
	}

	public String getContents() {
		return this.contents;
	}
}
