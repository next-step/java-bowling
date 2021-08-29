package qna.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import qna.CannotDeleteException;

class AnswersTest {

	@DisplayName("모든 답변의 작성자가 로그인 사용자와 같을 경우, 답변을 삭제할 수 있다.")
	@Test
	void deletable() throws Exception {
		Answers answers = new Answers();
		answers.add(new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "answer1"));
		answers.add(new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "answer2"));
		List<DeleteHistory> deleteHistories = answers.delete(UserTest.JAVAJIGI);

		assertThat(deleteHistories).hasSize(2);
	}

	@DisplayName("로그인 사용자 외의 작성자가 작성한 답변이 존재하는 경우, 모든 답변을 삭제할 수 없다.")
	@Test
	void notDeletable() {
		Answers answers = new Answers();
		answers.add(new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "answer1"));
		answers.add(new Answer(UserTest.SANJIGI, QuestionTest.Q1, "answer1"));

		assertThatThrownBy(() -> answers.delete(UserTest.JAVAJIGI))
			.isInstanceOf(CannotDeleteException.class);
	}

}
