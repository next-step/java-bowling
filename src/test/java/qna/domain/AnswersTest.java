package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AnswersTest {

    @Test
    @DisplayName("답변 중 하나라도 매개변수로 받은 작성자와 다르면 false 반환하는 것 테스트")
    void ownerDifferentIsAllOwnerReturnFalse() {
        Answers answers = createAnswers(AnswerTest.A1, AnswerTest.A2);
        assertFalse(answers.isAllOwner(UserTest.JAVAJIGI));
        answers = createAnswers(AnswerTest.A2, AnswerTest.A2);
        assertFalse(answers.isAllOwner(UserTest.JAVAJIGI));
    }

    @Test
    @DisplayName("모든 답변의 작성자가 매개변수로 받은 작성자와 같으면 true 반환하는 것 테스트")
    void allOwnerSameWithWriterIsAllOwnerReturnFalse() {
        Answers answers = createAnswers(AnswerTest.A1, AnswerTest.A1);
        assertTrue(answers.isAllOwner(UserTest.JAVAJIGI));
    }

    @Test
    @DisplayName("생성된 Answers의 list 사이즈가 맞는지 테스트")
    void getSizeTest() {
        Answers answers = createAnswers(AnswerTest.A1, AnswerTest.A2);
        assertThat(answers.get().size()).isEqualTo(2);
    }

    private Answers createAnswers(Answer a1, Answer a2) {
        Answers AS = new Answers();
        AS.add(a1);
        AS.add(a2);
        return AS;
    }
}