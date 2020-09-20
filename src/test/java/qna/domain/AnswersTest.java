package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import qna.CannotDeleteException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class AnswersTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    private static Stream<Arguments> parameterForAnswersTest() {
        return Stream.of(
                Arguments.of(
                        Collections.singletonList(
                                new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1")
                                //new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2")
                        )

                )
        )
                ;
    }

    @ParameterizedTest
    @MethodSource("parameterForAnswersTest")
    @DisplayName("기능이 정상적으로 작동했는지(Return이 Null이 아닌지) 확인")
    void initialize_Answers_And_Deletion(List<Answer> answerList) throws CannotDeleteException {
        Answers answers = Answers.of(answerList);
        assertThat(answers.delete(new User(1L,"javajigi", "password", "name", "javajigi@slipp.net"))).isNotNull();
    }

    @ParameterizedTest
    @MethodSource("parameterForAnswersTest")
    @DisplayName("두 번째 생성자를 이용해 id가 없을 때, 서로 다른유저임을 판별해 Exception 나는 것을 확인")
    void initialize_Answers_And_Exception(List<Answer> answerList) {
        Answers answers = Answers.of(answerList);
        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(
                        ()->answers.delete(new User("javajigi", "password", "name", "javajigi@slipp.net"))
                );
    }
}
