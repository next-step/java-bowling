package qna.domain;

import java.util.ArrayList;
import java.util.List;

import qna.CannotDeleteException;

public class Answers {

	private List<Answer> answers = new ArrayList<>();

	public void add(Answer answer) {
		answers.add(answer);
	}

	public List<DeleteHistory> delete(User loginUser) throws CannotDeleteException {
		List<DeleteHistory> deleteHistories = new ArrayList<>();
		for (Answer answer : answers) {
			deleteHistories.add(answer.delete(loginUser));
		}
		return deleteHistories;
	}
}
