package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswersTest {
    @Test
    @DisplayName("답변들 삭제 성공")
    void test() {
        // given
        Answers answers = new Answers(List.of(AnswerTest.A3));
        // when
        // then
        assertThat(answers.delete(UserTest.BADA)).containsExactly(new DeleteHistory(ContentType.ANSWER, 1L, UserTest.BADA));
    }

    @Test
    @DisplayName("답변들 삭제 실패 : 답변자들이 삭제자와 다를 경우")
    void test2() {
        // given
        Answers answers = new Answers(List.of(AnswerTest.A1));
        // when
        // then
        assertThatThrownBy(() -> {
            answers.delete(UserTest.BADA);
        }).isInstanceOf(CannotDeleteException.class);
    }

}
