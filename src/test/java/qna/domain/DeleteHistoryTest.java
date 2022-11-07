package qna.domain;

import static qna.domain.DeleteHistory.questionHistory;
import static qna.domain.QuestionTest.Q1;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeleteHistoryTest {

	@Test
	@DisplayName("question 삭제 기록을 남긴다")
	void deleteQuestionHistory(){
		Assertions.assertThat(questionHistory(Q1))
			.isInstanceOf(DeleteHistory.class);
	}

	@Test
	@DisplayName("answer 삭제 기록을 남긴다")
	void deleteAnswerHistory(){
		Assertions.assertThat(answerHistory(Q1))
			.isInstanceOf(DeleteHistory.class);
	}

}
