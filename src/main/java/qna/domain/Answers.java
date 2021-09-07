package qna.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.Where;

import qna.CannotDeleteException;

@Embeddable
public class Answers {
	@OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
	@Where(clause = "deleted = false")
	@OrderBy("id ASC")
	private List<Answer> answers = new ArrayList<>();

	public List<DeleteHistory> delete(User loginUser) throws CannotDeleteException {
		ArrayList<DeleteHistory> deleteHistories = new ArrayList<>();
		for (Answer answer : this.answers) {
			deleteHistories.add(answer.delete(loginUser));
		}
		return deleteHistories;
	}

	public void add(Answer answer) {
		this.answers.add(answer);
	}
}
