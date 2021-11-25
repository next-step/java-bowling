package qna.domain;

import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.Where;

@Embeddable
public class Answers {
	@OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
	@Where(clause = "deleted = false")
	@OrderBy("id ASC")
	private List<Answer> answers = new ArrayList<>();

	public void add(Answer answer) {
		answers.add(answer);
	}

	public boolean isAllOwner(User writer) {
		return answers.stream()
			.allMatch(answer -> answer.isOwner(writer));
	}

	public List<DeleteHistory> deleteAll() {
		return answers.stream()
			.map(Answer::delete)
			.collect(toList());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		Answers other = (Answers)obj;

		return Objects.equals(answers, other.answers);
	}

	@Override
	public int hashCode() {
		return Objects.hash(answers);
	}
}
