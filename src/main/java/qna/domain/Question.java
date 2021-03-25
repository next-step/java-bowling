package qna.domain;

import org.hibernate.annotations.Where;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import qna.CannotDeleteException;

@Entity
public class Question extends AbstractEntity implements MakeDeleteHistory {
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
	private List<Answer> answers = new ArrayList<>();

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

	@Override
	public String toString() {
		return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents + ", writer=" + writer + "]";
	}

	public boolean countUnauthorizedUserAnswer(User loginUser) {
		long unauthroizedUserAnswerCount = this.answers
			.stream()
			.filter(answer -> !answer.isOwner(loginUser))
			.count();

		return unauthroizedUserAnswerCount == 0;
	}

	public List<DeleteHistory> makeDeleteHistories() {

		List<DeleteHistory> deleteHistories = new ArrayList<>();
		deleteHistories.add(makeDeleteHistory());
		deleteHistories.addAll(
			answers.stream()
				.map(Answer::makeDeleteHistory)
				.collect(Collectors.toList())
		);
		return deleteHistories;
	}

	@Override
	public DeleteHistory makeDeleteHistory() {
		this.setDeleted(true);
		return new DeleteHistory(ContentType.QUESTION, super.getId(), this.getWriter(), LocalDateTime.now());
	}
}
