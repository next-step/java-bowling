package qna.domain.question;

import org.hibernate.annotations.Where;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import qna.CannotDeleteException;
import qna.domain.AbstractEntity;
import qna.domain.ContentType;
import qna.domain.user.User;
import qna.domain.answer.Answer;
import qna.domain.answer.Answers;
import qna.domain.history.DeleteHistory;

@Entity
public class Question extends AbstractEntity {
	@Column(length = 100, nullable = false)
	private String title;

	@Lob
	private String contents;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
	private User writer;

	@OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
	@Where(clause = "deleted = false")
	@OrderBy("id ASC")
	private final List<Answer> answers = new ArrayList<>();

	private boolean deleted = false;

	public Question() {
	}

	public Question(String title, String contents) {
		this.title = title;
		this.contents = contents;
	}

	public Question(long id, String title, String contents) {
		super(id);
		this.title = title;
		this.contents = contents;
	}

	public String getTitle() {
		return title;
	}

	public Question setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getContents() {
		return contents;
	}

	public Question setContents(String contents) {
		this.contents = contents;
		return this;
	}

	public User getWriter() {
		return writer;
	}

	public Question writeBy(User loginUser) {
		this.writer = loginUser;
		return this;
	}

	public void addAnswer(Answer answer) {
		answer.toQuestion(this);
		answers.add(answer);
	}

	public boolean isOwner(User loginUser) {
		return writer.equals(loginUser);
	}

	public Question setDeleted(boolean deleted) {
		this.deleted = deleted;
		return this;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public List<DeleteHistory> deleteQuestion(User loginUser) throws CannotDeleteException {
		checkAuthority(loginUser);
		Answers answers = new Answers(getAnswers(), loginUser);
		List<DeleteHistory> deleteHistories = new ArrayList<>();
		deleted(deleteHistories);
		answers.deleteAnswer(deleteHistories);
		return deleteHistories;
	}

	public List<DeleteHistory> deleted(List<DeleteHistory> deleteHistories) {
		setDeleted(true);
		deleteHistories.add(new DeleteHistory(ContentType.QUESTION, getId(), writer, LocalDateTime.now()));
		return deleteHistories;
	}

	private void checkAuthority(User loginUser) throws CannotDeleteException {
		if (!writer.equals(loginUser)) {
			throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
		}
	}

	@Override
	public String toString() {
		return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
	}

}
