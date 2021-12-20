package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AnswersTest {

    Answers AS1;
    Answers AS2;
    Answers AS3;

    @BeforeEach
    void setUp() {
        AS1 = new Answers();
        AS1.add(AnswerTest.A1);
        AS1.add(AnswerTest.A2);
        AS2 = new Answers();
        AS2.add(AnswerTest.A1);
        AS2.add(AnswerTest.A1);
        AS3 = new Answers();
        AS3.add(AnswerTest.A2);
        AS3.add(AnswerTest.A2);
    }

    @Test
    @DisplayName("답변 중 하나라도 매개변수로 받은 작성자와 다르면 false 반환하는 것 테스트")
    void ownerDifferentIsAllOwnerReturnFalse() {
        assertFalse(AS1.isAllOwner(UserTest.JAVAJIGI));
        assertFalse(AS3.isAllOwner(UserTest.JAVAJIGI));
    }

    @Test
    @DisplayName("모든 답변의 작성자가 매개변수로 받은 작성자와 같으면 true 반환하는 것 테스트")
    void allOwnerSameWithWriterIsAllOwnerReturnFalse() {
        assertTrue(AS2.isAllOwner(UserTest.JAVAJIGI));
    }

    @Test
    @DisplayName("생성된 Answers의 list 사이즈가 맞는지 테스트")
    void getSizeTest() {
        assertThat(AS1.get().size()).isEqualTo(2);
    }
    
    @Test
    @DisplayName("delete 함수 호출 후 isDeleted true로 되는 것 테스트")
    void afterDeleteIsDeletedTrueTest() {
        AS1.get().forEach(answer -> assertFalse(answer.isDeleted()));
        AS1.delete();
        AS1.get().forEach(answer -> assertTrue(answer.isDeleted()));
    }
}
