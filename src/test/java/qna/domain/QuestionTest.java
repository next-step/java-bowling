package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import qna.NotAuthorizedDeleteException;
import qna.NotOwnedDeleteException;

import java.net.ConnectException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @DisplayName("질문 삭제시 질문의 작성자가 아닐 경우 NotAuthorizedDeleteException 발생")
    @ParameterizedTest
    @MethodSource("provideQuestionAndNonWriter")
    public void 삭제_검증_테스트 (Question question, User writer) {
        assertThatExceptionOfType(NotAuthorizedDeleteException.class)
            .isThrownBy(() -> question.delete(writer));
    }

    private static Stream<Arguments> provideQuestionAndNonWriter () {
        return Stream.of(
          Arguments.of(Q1, UserTest.SANJIGI),
          Arguments.of(Q2, UserTest.JAVAJIGI)
        );
    }

    @DisplayName("질문의 작성자가 아닌 유저가 답변을 달았을 때 질문을 삭제할 경우 NotOwnedDeleteException 발생")
    @ParameterizedTest
    @MethodSource("provideQuestionOfAddedAnswerAndWriter")
    public void 삭제_검증_테스트_2 (Question question, User writer) {
        assertThatExceptionOfType(NotOwnedDeleteException.class)
            .isThrownBy(() -> question.delete(writer));
    }

    private static Stream<Arguments> provideQuestionOfAddedAnswerAndWriter () {
        Q1.addAnswer(AnswerTest.A1);
        Q1.addAnswer(AnswerTest.A2);
        Q2.addAnswer(AnswerTest.A1);
        Q2.addAnswer(AnswerTest.A2);
        return Stream.of(
          Arguments.of(Q1, UserTest.JAVAJIGI),
          Arguments.of(Q2, UserTest.SANJIGI)
        );
    }

    @DisplayName("질문 삭제가 정상적으로 이루어졌을 경우에 대한 테스트")
    @ParameterizedTest
    @MethodSource("provideQuestionAndWriter")
    public void 삭제_확인_테스트 (Question question, User writer, List<DeleteHistory> expected) throws Exception {
        assertEquals(expected, question.delete(writer));
    }

    private static Stream<Arguments> provideQuestionAndWriter () {
        Answer a1 = AnswerTest.A1;
        Answer a2 = AnswerTest.A2;
        Q1.addAnswer(a1);
        Q2.addAnswer(a2);
        return Stream.of(
          Arguments.of(
              Q1,
              UserTest.JAVAJIGI,
              Arrays.asList(
                  new DeleteHistory(ContentType.QUESTION, Q1.getId(), Q1.getWriter(), LocalDateTime.now()),
                  new DeleteHistory(ContentType.ANSWER, a1.getId(), a1.getWriter(), LocalDateTime.now())
              )
          ),
          Arguments.of(
              Q2,
              UserTest.SANJIGI,
              Arrays.asList(
                  new DeleteHistory(ContentType.QUESTION, Q2.getId(), Q2.getWriter(), LocalDateTime.now()),
                  new DeleteHistory(ContentType.ANSWER, a2.getId(), a2.getWriter(), LocalDateTime.now())
              )
          )
        );
    }
}
