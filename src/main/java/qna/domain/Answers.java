package qna.domain;

import java.util.Collections;
import java.util.List;

import qna.CannotDeleteException;

public class Answers {

	private final List<Answer> answers;

	public Answers(final List<Answer> answers, User loginUser) throws CannotDeleteException {
		checkAnswerAuthority(answers, loginUser);
		this.answers = Collections.unmodifiableList(answers);
	}

	private void checkAnswerAuthority(List<Answer> answers, User loginUser) throws CannotDeleteException {
		for (Answer answer : answers) {
			answer.checkAuthority(loginUser);
		}
	}

	public List<DeleteHistory> deleteAnswer(List<DeleteHistory> deleteHistories) {
		answers.forEach(answer -> {
			answer.setDeleted(true);
			deleteHistories.add(answer.deleteHistory());
		});
		return deleteHistories;
	}
}
