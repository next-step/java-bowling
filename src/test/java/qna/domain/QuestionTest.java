package qna.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import qna.CannotDeleteException;

public class QuestionTest {
	public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
	public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

	@DisplayName("전달받은 유저와 질문자가 다를경우 예외 발생")
	@Test
	void deleteWithInvalidWriter() {
		// given
		Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);

		// when then
		assertThatExceptionOfType(CannotDeleteException.class)
			.isThrownBy(() -> question.delete(UserTest.SANJIGI));
	}

	@DisplayName("전달받은 유저와 질문의 모든 답변자가 일치 하지 않을 경우 예외 발생")
	@Test
	void deleteWithInvalidAnswersWriter() {
		// given
		Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
		question.addAnswer(AnswerTest.A1);
		question.addAnswer(AnswerTest.A2);

		// when then
		assertThatExceptionOfType(CannotDeleteException.class)
			.isThrownBy(() -> question.delete(UserTest.JAVAJIGI));
	}

	@DisplayName("Question 삭제 처리시 상태와 반환값 검증")
	@Test
	void delete() {
		// given
		Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
		question.addAnswer(AnswerTest.A1);
		question.addAnswer(AnswerTest.A1);

		// when
		List<DeleteHistory> deleteHistories = question.delete(UserTest.JAVAJIGI);

		// then
		assertAll(
			() -> assertThat(question.isDeleted()).isTrue(),
			() -> assertThat(deleteHistories).hasSize(3)
		);
	}
}
