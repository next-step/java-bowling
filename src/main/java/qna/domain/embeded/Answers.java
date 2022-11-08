package qna.domain.embeded;

import org.hibernate.annotations.Where;
import qna.domain.Answer;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by seungwoo.song on 2022-11-07
 */
@Embeddable
public class Answers extends AbstractList<Answer> {

	@OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
	@Where(clause = "deleted = false")
	@OrderBy("id ASC")
	private List<Answer> answers = new ArrayList<>();

	public Answers(List<Answer> answers) {
		this.answers = answers;
	}

	public Answers() {
	}

	public void deleteAll() {
		answers.forEach(Answer::delete);

	}

	@Override
	public Answer get(int index) {
		return answers.get(index);
	}

	@Override
	public int size() {
		return answers.size();
	}

	@Override
	public boolean add(Answer answer) {
		return answers.add(answer);
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public boolean isDeletedAll() {
		return !answers.stream()
			.filter(Answer::isNotDeleted)
			.findAny()
			.isPresent();
	}
}
