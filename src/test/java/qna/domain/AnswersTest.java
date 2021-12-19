package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.exception.CannotDeleteException;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswersTest {
    public static final Answers ANSWERS = new Answers(Arrays.asList(AnswerTest.A1, AnswerTest.A2));

    @Test
    @DisplayName("Answers 생성자 테스트")
    void constructor() {
        assertThat(ANSWERS)
                .isEqualTo(new Answers(Arrays.asList(AnswerTest.A1, AnswerTest.A2)));
    }

    @Test
    @DisplayName("답변을 삭제할 권한이 없으면 Exception이 발생한다.")
    void validateDelete() {
        assertThatThrownBy(() -> {
            ANSWERS.validateDelete(UserTest.SANJIGI);
        }).isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("Answers delete")
    void delete() {
//        ANSWERS.delete(UserTest.SANJIGI);
    }
}
