package qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

	public Answers() {
	}

	public Answer addAnswer(Answer answer) {
		answers.add(answer);
		return answer;
	}

	public List<DeleteHistory> deleteAll(User loginUser) throws CannotDeleteException {
		validateAnswersOwner(loginUser);
		return answers.stream()
			.peek(answer -> {
				try {
					answer.delete();
				} catch (CannotDeleteException ignored) {
				}
			})
			.map(answer ->
				new DeleteHistory(ContentType.ANSWER, answer.getId(), loginUser, LocalDateTime.now()))
			.collect(Collectors.toList());
	}

	private void validateAnswersOwner(User loginUser) throws CannotDeleteException {
		if (answers.stream().anyMatch(answer -> ! answer.isOwner(loginUser))) {
			throw new CannotDeleteException("다른 사람이 쓴 답변이 존재하므로 삭제할 수 없습니다.");
		}
	}

	public List<Answer> getAnswers() {
		return answers;
	}
}
