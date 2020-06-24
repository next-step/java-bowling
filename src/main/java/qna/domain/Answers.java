package qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import qna.CannotDeleteException;

public class Answers {
	
	private List<Answer> answers = new ArrayList<>();
	
	public void add(Answer answer) {
		answers.add(answer);
	}
	
	public DeleteHistories deleteByOthers(User user) throws CannotDeleteException {
		List<DeleteHistory> deleteHistories = new ArrayList<>();
		
		for(Answer answer : answers) {
			deleteHistories.add(answer.deleteByOthers(user));
		}
		return new DeleteHistories(deleteHistories);
	}
	
	
}
