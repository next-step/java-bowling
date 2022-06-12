package qna.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import qna.CannotDeleteException;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class AnswersTest {
    private static Stream<Arguments> answersOwnerExceptionArgs() {
        return Stream.of(
                arguments(UserTest.JAVAJIGI, AnswerTest.A2),
                arguments(UserTest.SANJIGI, AnswerTest.A1)
        );
    }

    @ParameterizedTest
    @MethodSource("answersOwnerExceptionArgs")
    @DisplayName("다른 사람이 쓴 답변이 있어서 삭제를 못하는 경우 예외를 던진다")
    void validateOwnerTest(User loginUser, Answer answer) {
        Answers answers = new Answers();
        answers.addAnswer(answer);

        Assertions.assertThrows(CannotDeleteException.class , () ->
                answers.searchAnswers(loginUser)
        );
    }
}