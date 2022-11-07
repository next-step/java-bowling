package qna.domain;

import java.time.LocalDateTime;
import java.util.function.Predicate;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import qna.CannotDeleteException;

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

	public void isOwner(User loginUser) throws CannotDeleteException {
		if (!writer.equals(loginUser)) {
			throw new CannotDeleteException("로그인한 사용자와 일치하지 않습니다");
		}
	}

	public void delete() {
		this.deleted = true;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void isAnswerOwner(User user) throws CannotDeleteException {
		for (Answer answer : answers) {
			answer.isOwner(user);
		}
	}

	@Override
	public String toString() {
		return "Question [id=" + Id() + ", title=" + title + ", contents=" + contents
			+ ", writer=" + writer + "]";
	}

	public DeleteHistory questionHistory() {
		return new DeleteHistory(ContentType.QUESTION, this.Id(), writer, LocalDateTime.now());
	}

	public void deleteAnswer(){
		answers.stream()
			.forEach(answer -> answer.delete());
	}

	public boolean isAnswersDeleted(){
		return answers.stream()
			.map(answer -> answer.isDeleted())
			.allMatch(Predicate.isEqual(true));
	}
}
