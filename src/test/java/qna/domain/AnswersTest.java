package qna.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import qna.CannotDeleteException;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName(value = "답변 리스트 테스트")
class AnswersTest {

	private Answer answer1;
	private Answer answer2;
	private Answers answers;

	@BeforeEach
	void setUp() {

		answer1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
		answer2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

		answers = new Answers(List.of(answer1));
	}

	@Test
	void 동등성_테스트() {
		Answer copyOfAnswer1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");

		assertThat(answers).isEqualTo(new Answers(List.of(copyOfAnswer1)));
	}

	@Test
	void 답변_추가() {
		Answers expect = new Answers(List.of(answer1, answer2));

		answers.add(answer2);

		assertThat(answers).isEqualTo(expect);
	}

	@Test
	void 답변_추가_시_입력이_널이면_예외() {
		assertThatNullPointerException().isThrownBy(
			() -> answers.add(null)
		);
	}

	@Test
	void 삭제_테스트() throws CannotDeleteException {
		List<DeleteHistory> deleteHistories = answers.delete(UserTest.JAVAJIGI);
		assertAll(
			() -> assertThat(answer1.isDeleted()).isTrue(),
			() -> assertThat(deleteHistories).hasSize(1)
		);

	}

	@Test
	void 삭제_시_입력이_널이면_예외() {
		assertThatNullPointerException().isThrownBy(
			() -> answers.delete(null)
		);
	}

	@Test
	void 삭제_시_로그인_유저와_작성자가_다르면_예외() {
		assertThatThrownBy(
			() -> answers.delete(UserTest.SANJIGI)
		).isExactlyInstanceOf(CannotDeleteException.class);
	}

}