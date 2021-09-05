package qna.domain;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static qna.domain.AnswerTest.A1;
import static qna.domain.AnswerTest.A2;
import static qna.domain.UserTest.JAVAJIGI;

class AnswersTest {

    @Test
    @DisplayName("답변 삭제 - 다른 답변자 있는 경우")
    public void 답변삭제_다른답변자있음() {
        // given
        String message = "다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.";
        Answers answers = new Answers(Arrays.asList(A1, A2));

        // when
        ThrowingCallable throwingCallable = () -> answers.delete(JAVAJIGI);

        // then
        assertThatThrownBy(throwingCallable)
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage(message);
    }

    @Test
    @DisplayName("답변 삭제 - 다른 답변자가 없는 경우")
    public void 답변삭제_질문자와답변자가같음() {
        // given
        Answers answers = new Answers(Collections.singletonList(A1));
        DeleteHistory answerDeleteHistory = DeleteHistory.answerOf(A1.getId(), JAVAJIGI);

        // when
        List<DeleteHistory> deleteHistories = answers.delete(JAVAJIGI);

        // then
        assertThat(deleteHistories).containsExactly(answerDeleteHistory);
    }

}