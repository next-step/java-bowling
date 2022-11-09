package qna.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeleteHistoryTest {

	private Question question;
	private Answer answer;

	@BeforeEach
	public void setUp() {
		question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
		answer = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
		question.addAnswer(answer);
	}

	@Test
	@DisplayName("question 와 포함된 answer 를 delete history 로 변환한다.")
	public void Given_Question_Then_ReturnDeleteHistory() {
		List<DeleteHistory> deleteHistories = DeleteHistory.addAllByQuestion(question, UserTest.JAVAJIGI);
		assertThat(deleteHistories).hasSize(2);
	}
}
