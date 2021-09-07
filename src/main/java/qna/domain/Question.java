package qna.domain;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import qna.CannotDeleteException;

@Entity
public class Question extends AbstractEntity {
	public static final String NO_PERMISSION_FOR_QUESTION = "질문을 삭제할 권한이 없습니다.";
	@Embedded
	private QuestionContext questionContext;

	@Embedded
	private Answers answers;

	public Question() {
	}

	public Question(String title, String contents) {
		this.questionContext = new QuestionContext(title, contents);
	}

	public Question(long id, String title, String contents) {
		super(id);
		this.questionContext = new QuestionContext(title, contents);
		this.answers = new Answers();
	}

	public String getTitle() {
		return this.questionContext.getTitle();
	}

	public Question setTitle(String title) {
		this.questionContext.setTitle(title);
		return this;
	}

	public String getContents() {
		return this.questionContext.getContents();
	}

	public Question setContents(String contents) {
		this.questionContext.setContents(contents);
		return this;
	}

	public User getWriter() {
		return this.questionContext.getWriter();
	}

	public Question writeBy(User loginUser) {
		this.questionContext.writeBy(loginUser);
		return this;
	}

	public void addAnswer(Answer answer) {
		answer.toQuestion(this);
		this.answers.add(answer);
	}

	public boolean isOwner(User loginUser) {
		return this.questionContext.isOwner(loginUser);
	}

	public Question setDeleted(boolean deleted) {
		this.questionContext.setDeleted(deleted);
		return this;
	}

	public boolean isDeleted() {
		return this.questionContext.isDeleted();
	}

	public Answers getAnswers() {
		return this.answers;
	}

	public List<DeleteHistory> delete(User loginUser) throws CannotDeleteException {
		if (this.isOwner(loginUser)) {
			ArrayList<DeleteHistory> deleteHistories = new ArrayList<>();
			this.setDeleted(true);
			deleteHistories.add(
				new DeleteHistory(ContentType.QUESTION, this.getId(), this.getWriter(), LocalDateTime.now()));
			deleteHistories.addAll(answers.delete(loginUser));
			return deleteHistories;
		}
		throw new CannotDeleteException(NO_PERMISSION_FOR_QUESTION);
	}

	@Override
	public String toString() {
		return "Question [id=" + getId() + ", title=" + this.questionContext.getTitle() + ", contents="
			+ this.questionContext.getContents() + ", writer=" + this.getWriter() + "]";
	}
}
