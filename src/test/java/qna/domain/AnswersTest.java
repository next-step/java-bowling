package qna.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AnswersTest {

	private Answer answer1;
	private Answer answer2;

	@BeforeEach
	void setup() {
		this.answer1 = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
		this.answer2 = new Answer(22L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents2");
	}

	@Test
	void delete() throws Exception {
		Answers answers = new Answers(Arrays.asList(this.answer1, this.answer2));
		answers.delete(UserTest.JAVAJIGI);
		assertThat(this.answer1.isDeleted()).isTrue();
		assertThat(this.answer2.isDeleted()).isTrue();
	}
}
