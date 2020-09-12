package qna.domain;

import java.util.ArrayList;
import java.util.List;

import qna.CannotDeleteException;

public class Answers {
	private final List<Answer> answers;

	private Answers(List<Answer> answers) {
		this.answers = answers;
	}

	public static Answers of(List<Answer> answers) {
		return new Answers(answers);
	}


	public DeleteHistories deleteAnswers(User deleteUser) throws CannotDeleteException {
		List<DeleteHistory> deleteHistories = new ArrayList<>(answers.size());
		for (Answer answer : answers) {
			deleteHistories.add(answer.delete(deleteUser));
		}

		return DeleteHistories.of(deleteHistories);
	}


}
