package qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import qna.CannotDeleteException;

@Entity
public class Question extends AbstractEntity {

	@Embedded
	private QuestionBody questionBody;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
	private User writer;

	@Embedded
	private Answers answers;

	private boolean deleted = false;

	public Question() {
	}

	public Question(String title, String contents) {
		this.questionBody = new QuestionBody(title, contents);
		this.answers = new Answers();
	}

	public Question(long id, String title, String contents) {
		super(id);
		this.questionBody = new QuestionBody(title, contents);
		this.answers = new Answers();
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

	public boolean isDeleted() {
		return deleted;
	}

	public Answers getAnswers() {
		return answers;
	}

	@Override
	public String toString() {
		return "Question{" +
			"questionBody=" + questionBody +
			", writer=" + writer +
			", answers=" + answers +
			", deleted=" + deleted +
			'}';
	}

	public List<DeleteHistory> delete(User loginUser) {
		List<DeleteHistory> deleteHistories = new ArrayList<>();
		deleteHistories.add(deleteQuestion(loginUser));
		deleteHistories.addAll(answers.deleteAll(loginUser));
		return deleteHistories;
	}

	private DeleteHistory deleteQuestion(User loginUser) {
		if (!this.isOwner(loginUser)) {
			throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
		}
		this.deleted = true;

		return new DeleteHistory(ContentType.QUESTION, this.getId(), this.getWriter(), LocalDateTime.now());
	}
}
