package qna.domain;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    @DisplayName("질문 삭제")
    void delete() {
        //given
        Q1.delete();

        //when
        boolean actual = Q1.isDeleted();

        //then
        assertThat(actual).isTrue();

    }

    @ParameterizedTest(name = "질문 작성자 일치 여부 {index} [{arguments}]")
    @MethodSource("deletable")
    @DisplayName("삭제 가능 여부 - 질문 작성자 일치 여부")
    void deletable(User loginUser, boolean expected) {
        //given
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);

        //when
        boolean actual = question.deletable(loginUser);

        //then
        assertThat(actual).isEqualTo(expected);

    }

    private static Stream<Arguments> deletable() {
        return Stream.of(
                Arguments.of(UserTest.JAVAJIGI, true),
                Arguments.of(UserTest.SANJIGI, false)
        );
    }


    @ParameterizedTest(name = "질문 작성자, 답변 작성자 일치 여부 {index} [{arguments}]")
    @MethodSource("deletable_with_answers")
    @DisplayName("삭제 가능 여부 - 질문 작성자 and 답변 작성자 일치 여부")
    void deletable_with_answers(User loginUser, Answers answers, boolean expected) {
        //given
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        question.addAnswers(answers);

        //when
        boolean actual = question.deletable(loginUser);

        //then
        assertThat(actual).isEqualTo(expected);

    }
    private static Stream<Arguments> deletable_with_answers() {
        return Stream.of(
                Arguments.of(UserTest.JAVAJIGI, AnswersTest.ANSWERS1, true),
                Arguments.of(UserTest.JAVAJIGI, AnswersTest.ANSWERS2, false)
        );
    }
}
