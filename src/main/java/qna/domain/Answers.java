package qna.domain;

import org.hibernate.annotations.Where;
import qna.CannotDeleteException;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class Answers {
	@OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
	@Where(clause = "deleted = false")
	@OrderBy("id ASC")
	private final List<Answer> answers;

	public Answers() {
		this.answers = new ArrayList<>();
	}

	public void addAnswer(Answer answer) {
		answers.add(answer);
	}

	public void validAnswersCanDelete(User loginUser) throws CannotDeleteException {
		for (Answer answer : answers) {
			answer.validAnswerCanDelete(loginUser);
		}
	}

	public List<DeleteHistory> delete() {
		List<DeleteHistory> deleteHistories = new ArrayList<>();
		for (Answer answer : answers) {
			deleteHistories.add(answer.setDeleted());
		}
		return deleteHistories;
	}
}
