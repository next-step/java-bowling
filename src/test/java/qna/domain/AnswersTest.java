package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class AnswersTest {

    @DisplayName("Answers 인스턴스 생성 여부 테스트")
    @Test
    void 생성() {

        Answers answers = new Answers();

        assertThat(answers).isNotNull();
    }

    @DisplayName("Answers 인스턴스 리스트로 생성 여부 테스트")
    @Test
    void 생성_리스트() {
        // given
        List<Answer> answerList = new ArrayList<>();

        Answers answers = new Answers(answerList);

        assertThat(answers).isNotNull();
    }

    @DisplayName("삭제가 되었을 때 반환 값으로 삭제 내역들을 기록한 객체를 반환하는지 테스트")
    @Test
    void 반환_삭제기록들() {
        // given
        List<Answer> answerList = new ArrayList<>(Arrays.asList(AnswerTest.A1, AnswerTest.A2));

        Answers answers = new Answers(answerList);
        assertAll(
                () -> assertThat(answers.delete(UserTest.JAVAJIGI)).isNotNull(),
                () -> assertThat(answers.delete(UserTest.JAVAJIGI)).isInstanceOf(DeleteHistories.class)
        );
    }
}
