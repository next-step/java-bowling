package step1.qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import step1.qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");


	@Test
	@DisplayName("다른 사람이 쓴 답변은 지울 수 없어야 한다.")
	void checkDeleteCondition() {
		assertThatThrownBy(() -> A1.checkDeleteCondition(UserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
		assertThatThrownBy(() -> A2.checkDeleteCondition(UserTest.JAVAJIGI)).isInstanceOf(CannotDeleteException.class);
		assertDoesNotThrow(() -> A1.checkDeleteCondition(UserTest.JAVAJIGI));
		assertDoesNotThrow(() -> A2.checkDeleteCondition(UserTest.SANJIGI));
	}

	@Test
	@DisplayName("삭제된 후에 제대로 기록이 처리되는지 확인")
	void deleteAndRecord(){
		List<DeleteHistory> deleteHistories = new ArrayList<>();
		assertFalse(A1.isDeleted());
		A1.deleteAndRecord(deleteHistories);
		assertTrue(A1.isDeleted());
		assertEquals(deleteHistories.get(0).getDeletedBy(), UserTest.JAVAJIGI);

        assertFalse(A2.isDeleted());
        A2.deleteAndRecord(deleteHistories);
        assertTrue(A2.isDeleted());
		assertEquals(deleteHistories.get(1).getDeletedBy(), UserTest.SANJIGI);
	}
}
