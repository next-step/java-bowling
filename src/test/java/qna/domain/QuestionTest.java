package qna.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import qna.CannotDeleteException;

public class QuestionTest {

	public static Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
	public static Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

	private Question question;

	@BeforeEach
	void setUp() {
		question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
	}

	@Test
	@DisplayName("본인 질문 정상 삭제")
	void validDeleteTest() throws CannotDeleteException {
		question.deleteQuestion(UserTest.JAVAJIGI);
		assertThat(question.isDeleted()).isTrue();

	}

	@Test
	@DisplayName("본인 질문이 아닌 사람의 글 삭제")
	void invalidDeleteTest() {
		assertThatThrownBy(() -> {
			question.deleteQuestion(UserTest.SANJIGI);
		}).isInstanceOf(CannotDeleteException.class).hasMessage("질문을 삭제할 권한이 없습니다.");
	}

}
