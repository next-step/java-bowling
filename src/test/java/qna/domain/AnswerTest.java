package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("삭제 테스트")
    void testDelete() {
        Answer sample = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "ABCD");

        assertThat(sample.isDeleted()).isFalse();

        sample.delete();

        assertThat(sample.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("삭제시 정상적으로 히스토리를 반환하는 지 테스트")
    void testDeleteHistoryReturn() {
        Answer sample = new Answer(1L, UserTest.JAVAJIGI, QuestionTest.Q1, "ABCD");

        DeleteHistory history = sample.delete();

        DeleteHistory expected = DeleteHistory.ofAnswer(sample.getId(), sample.getWriter());

        assertThat(history).isEqualTo(expected);
    }

    @Test
    @DisplayName("DeleteHistory 반환 방식 추가")
    void testAdditionalDeleteHistoryReturnMethod() {
        Answer sample = new Answer(1L, UserTest.JAVAJIGI, QuestionTest.Q1, "ABCD");
        List<DeleteHistory> expected = Arrays.asList(DeleteHistory.ofAnswer(sample.getId(), sample.getWriter()));

        List<DeleteHistory> result = new ArrayList<>();

        assertThat(sample.delete(result)).isEqualTo(expected);
    }

    @Test
    @DisplayName("login 유저가 소유자가 아닐 때의 예외 처리")
    void testValidate() {
        assertThatThrownBy(
                () -> new Answer(1L, UserTest.JAVAJIGI, QuestionTest.Q1, "ABCD").validateOwnership(UserTest.SANJIGI)
        );
    }
}
