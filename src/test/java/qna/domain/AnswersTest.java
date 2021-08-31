package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class AnswersTest {

    public static final Answers ANSWERS1 = new Answers(Arrays.asList(AnswerTest.A1, AnswerTest.A1));
    public static final Answers ANSWERS2 = new Answers(Arrays.asList(AnswerTest.A1, AnswerTest.A2));

    private static Stream<Arguments> all_answers_have_the_same_writer_and_question_writer() {
        return Stream.of(
                Arguments.of(ANSWERS1, true),
                Arguments.of(ANSWERS2, false)
        );
    }

    @ParameterizedTest(name = "모든 답변자와 질문자 동일 {index} [{arguments}]")
    @MethodSource("all_answers_have_the_same_writer_and_question_writer")
    @DisplayName("모든 답변자와 질문자 동일")
    void all_answers_have_the_same_writer_and_question_writer(Answers answers, boolean expected) throws Exception {
        //given

        //when
        boolean actual = answers.deletableBy(UserTest.JAVAJIGI);

        //then
        assertThat(actual).isEqualTo(expected);

    }

    @Test
    @DisplayName("모든 답변 삭제")
    void delete() throws Exception {
        //given
        ANSWERS1.delete();

        //when
        boolean actual = ANSWERS1.getAnswers()
                .stream()
                .allMatch(Answer::isDeleted);

        //then
        assertThat(actual).isTrue();

    }

}