package qna.service;

import java.util.List;

import org.springframework.stereotype.Service;

import qna.CannotDeleteException;
import qna.domain.Answer;
import qna.domain.User;

@Service("answerService")
public class AnswerService {

	public void checkOwnerOrThrow(User loginUser, List<Answer> answers) {
		answers.stream()
			.filter(answer -> !answer.isOwner(loginUser))
			.findAny()
			.ifPresent(answer -> {
				throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
			});
		;
	}

	public List<Answer> executeSoftDelete(User loginUser, List<Answer> answers) {
		checkOwnerOrThrow(loginUser, answers);
		answers.forEach(answer ->
			answer.setDeleted(true));
		return answers;
	}
}
