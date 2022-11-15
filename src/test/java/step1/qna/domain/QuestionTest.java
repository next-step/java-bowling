package step1.qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import step1.qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

	@Test
	@DisplayName("질문자가 아닌 사람이 질문을 삭제할 수 없다.")
	void checkDeleteCondition() {
		assertThatThrownBy(() -> Q1.checkDeleteCondition(UserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
		assertThatThrownBy(() -> Q2.checkDeleteCondition(UserTest.JAVAJIGI)).isInstanceOf(CannotDeleteException.class);
		assertDoesNotThrow(() -> Q1.checkDeleteCondition(UserTest.JAVAJIGI));
		assertDoesNotThrow(() -> Q2.checkDeleteCondition(UserTest.SANJIGI));
	}

	@Test
	@DisplayName("삭제된 후에 제대로 기록이 처리되는지 확인")
	void deleteAndRecordTest(){
		List<DeleteHistory> deleteHistories = new ArrayList<>();
		assertFalse(Q1.isDeleted());
		Q1.deleteAndRecord(deleteHistories);
		assertTrue(Q1.isDeleted());
		assertEquals(deleteHistories.get(0).getDeletedBy(), UserTest.JAVAJIGI);

		assertFalse(Q2.isDeleted());
		Q2.deleteAndRecord(deleteHistories);
		assertTrue(Q2.isDeleted());
		assertEquals(deleteHistories.get(1).getDeletedBy(), UserTest.SANJIGI);
	}
}
