package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class QuestionTest {

    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    @DisplayName("addAnswer 후 데이터 추가와 사이즈 증가 확인")
    void afterAddAnswerSizeIncreaseAndDataCheckTest() {
        int beforeSize = Q1.getAnswers().get().size();
        Q1.addAnswer(AnswerTest.A1);
        assertThat(Q1.getAnswers().get().size()).isEqualTo(beforeSize + 1);
        assertThat(Q1.getAnswers().get().get(beforeSize)).isEqualTo(AnswerTest.A1);
    }

    @Test
    @DisplayName("delete() 후 isDeleted true 되는 것 테스트")
    void afterDeleteIsDeletedReturnTrue() {
        assertFalse(Q2.isDeleted());
        Q2.delete();
        assertTrue(Q2.isDeleted());
    }
}
