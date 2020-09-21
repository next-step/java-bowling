package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    private static Stream<Arguments> parameterForQuestionTest() {
        return Stream.of(
                Arguments.of(
                        Q1
                )
        );
    }

    @ParameterizedTest
    @MethodSource("parameterForQuestionTest")
    @DisplayName("Question 생성 및 삭제기능 Test-DeleteHistory가 정상적으로 반환?")
    void initialize_Question(Question value) throws CannotDeleteException {
        assertThat(value.delete(new User(1L, "javajigi", "password", "name", "javajigi@slipp.net"), LocalDateTime.of(2020,5,21,14,23,11))).isNotNull();
    }

    @ParameterizedTest
    @MethodSource("parameterForQuestionTest")
    @DisplayName("Question 생성, 삭제에서 Exception 체크 - User의 생성자를 조금 다르게 이용")
    void initialize_Question_And_Exception(Question value) {
        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(
                        () -> value.delete(new User( "javajigi", "password", "name", "javajigi@slipp.net"), LocalDateTime.of(2020,5,21,14,23,11))
                ).withMessageMatching("질문을 삭제할 권한이 없습니다.");
    }
}
