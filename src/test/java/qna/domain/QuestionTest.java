package qna.domain;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static qna.domain.AnswerTest.A1;
import static qna.domain.AnswerTest.A2;
import static qna.domain.UserTest.*;
import static qna.domain.UserTest.JAVAJIGI;

public class QuestionTest {
    public static final Question Q1 = new Question(1L, "title1", "contents1").writeBy(JAVAJIGI);
    public static final Question Q2 = new Question(2L, "title2", "contents2").writeBy(SANJIGI);

    @Test
    @DisplayName("질문 삭제 - 삭제 권한 없는 경우")
    public void 질문삭제_삭제권한없음() {
        // given
        String message = "질문을 삭제할 권한이 없습니다.";

        // when
        ThrowingCallable throwingCallable = () -> Q2.delete(JAVAJIGI);

        // then
        assertThatThrownBy(throwingCallable)
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage(message);
    }

    @Test
    @DisplayName("질문 삭제 - 권한 있는 경우")
    public void 질문삭제_삭제권한있음() {
        // given
        DeleteHistory deleteHistory = DeleteHistory.questionOf(Q1.getId(), JAVAJIGI);

        // when
        List<DeleteHistory> deleteHistories = Q1.delete(JAVAJIGI);
        
        // then
        assertThat(deleteHistories).containsExactly(deleteHistory);
    }

    @Test
    @DisplayName("질문 삭제 - 질문자와 다른 답변자 있는 경우")
    public void 질문삭제_다른답변자있음() {
        // given
        String message = "다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.";

        // when
        Q1.addAnswer(A2);
        ThrowingCallable throwingCallable = () -> Q1.delete(JAVAJIGI);

        // then
        assertThatThrownBy(throwingCallable)
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage(message);
    }

    @Test
    @DisplayName("질문 삭제 - 질문자와 답변자가 같은 경우")
    public void 질문삭제_질문자와답변자가같음() {
        // given
        DeleteHistory questionDeleteHistory = DeleteHistory.questionOf(Q1.getId(), JAVAJIGI);
        DeleteHistory answerDeleteHistory = DeleteHistory.answerOf(A1.getId(), JAVAJIGI);

        // when
        Q1.addAnswer(A1);
        List<DeleteHistory> deleteHistories = Q1.delete(JAVAJIGI);

        // then
        assertThat(deleteHistories)
                .containsExactly(questionDeleteHistory, answerDeleteHistory);
    }

}
