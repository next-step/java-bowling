package qna.domain;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswersTest {

    @Test
    public void createInstanceTest() {
        //Given & When
        List<Answer> answerList = Arrays.asList(AnswerTest.A1, AnswerTest.A2);
        Answers answers = new Answers(answerList);

        //Then
        assertThat(answers.size()).isEqualTo(answerList.size());
    }

    @Test
    public void addAnswerTest(){
        //Given
        Answers answers = new Answers();

        //When
        answers.addAnswer(new Answer());

        //Then
        assertThat(answers.size()).isEqualTo(1);
    }

    @Test
    public void delete_AnswerTest() {
        //Given
        Answers answers = new Answers(Arrays.asList(AnswerTest.A1));

        //When
        answers.delete(UserTest.JAVAJIGI);

        //Then
        assertThat(AnswerTest.A1.isDeleted()).isTrue();
    }

    @ParameterizedTest
    @MethodSource("createAnswers")
    public void createDeleteHistoriesTest(List<Answer> parameterAnswers, User loginUser) {
        //Given
        Answers answers = new Answers(parameterAnswers);

        //When
        answers.delete(loginUser);
        List<DeleteHistory> deleteHistories = answers.addDeleteHistories();

        //Then
        assertThat(deleteHistories).hasSize(parameterAnswers.size());
    }

    private static Stream<Arguments> createAnswers() {
        return  Stream.of(
                Arguments.of(Arrays.asList(
                                new Answer(11L, UserTest.JAVAJIGI, new Question(), "contents1"),
                                new Answer(12L, UserTest.JAVAJIGI, new Question(), "contents1")
                            ), UserTest.JAVAJIGI
        ));
    }
}
