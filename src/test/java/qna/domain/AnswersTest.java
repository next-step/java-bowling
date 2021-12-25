package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AnswersTest {
    public static final Answers JAVAJIGI_SANJIGI_ANSWERS = new Answers(Arrays.asList(AnswerTest.A1, AnswerTest.A2));
    public static final Answers SANJIGI_ANSWERS = new Answers(Arrays.asList(AnswerTest.A2, AnswerTest.A3));

    @DisplayName("답변들 중 하나라도 다른 사람의 답변이 있으면 삭제가 불가능")
    @Test
    void 답변들_삭제실패() {
        assertThatThrownBy(() -> JAVAJIGI_SANJIGI_ANSWERS.delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @DisplayName("본인 답변만 있으면 삭제 가능")
    @Test
    void 답변들_삭제성공() {
        DeleteHistories expected = new DeleteHistories(Arrays.asList(new DeleteHistory(AnswerTest.A2), new DeleteHistory(AnswerTest.A3)));
        assertThat(SANJIGI_ANSWERS.delete(UserTest.SANJIGI)).isEqualTo(expected);
    }

}