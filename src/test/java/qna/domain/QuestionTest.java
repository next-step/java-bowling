package qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);


    @ParameterizedTest
    @MethodSource("providedDeletedState")
    @DisplayName("질문 삭제 요청이 오면 질문을 '삭제상태'로 변경한다.")
    void changeQuestionInDeleteStateWhenRequestForDeleteSubmitted(Question question,
                                                                  boolean requestState,
                                                                  boolean expectedState) {
        assertEquals(expectedState, question.setDeleted(requestState).isDeleted());
    }

    private static Stream<Arguments> providedDeletedState() {
        return Stream.of(
                arguments(Q1, true, true),
                arguments(Q2, true, true)
        );
    }

    @Test
    @DisplayName("로그인 사용자와 질문한 사람이 같은 경우 삭제 가능")
    void isLoginUserAndQuestionWriterSame() {

    }

    @Test
    @DisplayName("답변이 없는 경우 삭제가 가능하다")
    void isAnswerListNull() {

    }

    @Test
    @DisplayName("답변자가 같은 경우 삭제가 가능하다")
    void isAnswerWriterAndQuestionOwnerSame() {

    }

}
