package qna.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import qna.CannotDeleteException;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    private static Stream<Arguments> questionOwnerExceptionArgs() {
        return Stream.of(
                arguments(UserTest.JAVAJIGI, Q2),
                arguments(UserTest.SANJIGI, Q1)
        );
    }

    @ParameterizedTest
    @MethodSource("questionOwnerExceptionArgs")
    @DisplayName("질문을 삭제할 권한이 없으면 예외를 던진다.")
    public void validateOwnerTest(User loginUser, Question question) {
        Assertions.assertThrows(CannotDeleteException.class , () ->
                question.deleteAnswers(loginUser, 0)
        );
    }
}
