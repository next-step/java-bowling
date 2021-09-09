package qna.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Embeddable
public class QuestionContext {
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
	private User writer;

	@Column(length = 100, nullable = false)
	private String title;

	@Lob
	private String contents;

	private boolean deleted = false;

	public QuestionContext(String title, String contents) {
		this.title = title;
		this.contents = contents;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return this.contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public User getWriter() {
		return this.writer;
	}

	public void writeBy(User loginUser) {
		this.writer = loginUser;
	}

	public boolean isOwner(User loginUser) {
		return this.writer.equals(loginUser);
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public boolean isDeleted() {
		return this.deleted;
	}
}
