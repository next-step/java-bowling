package qna.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
	private List<Answer> values = new ArrayList<>();

	protected Answers() {
	}

	public Answers(List<Answer> values) {
		this.values.addAll(Objects.requireNonNull(values));
	}

	public void add(Answer answer) {
		values.add(Objects.requireNonNull(answer));
	}

	public List<DeleteHistory> delete(User loginUser) throws CannotDeleteException {
		Objects.requireNonNull(loginUser);
		List<DeleteHistory> result = new ArrayList<>();
		for (Answer answer : values) {
			result.add(answer.delete(loginUser));
		}
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Answers answers = (Answers)o;
		return Objects.equals(values, answers.values);
	}

	@Override
	public int hashCode() {
		return Objects.hash(values);
	}
}
