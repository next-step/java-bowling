package qna.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.Where;

public class Answers {

	@OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
	@Where(clause = "deleted = false")
	@OrderBy("id ASC")
	private final List<Answer> answers = new ArrayList<>();

	public void add(Answer answer) {
		answers.add(answer);
	}

	public List<DeleteHistory> delete(User loginUser) {
		return answers.stream()
			.map(answer -> answer.delete(loginUser))
			.collect(Collectors.toList());
	}
}