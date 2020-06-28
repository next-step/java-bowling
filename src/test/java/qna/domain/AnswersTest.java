package qna.domain;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import qna.CannotDeleteException;
import qna.ExistTheOthersAnswerException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswersTest {

    @ParameterizedTest
    @DisplayName("답변에 내가 쓰지 않은 타인의 답변이 있을 때 삭제 예외처리 테스트")
    @MethodSource("provideTheOthersAnswers")
    void invalidDeleteAnswersTest(User user, Answers answers) {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        assertThatThrownBy(() -> answers.delete(user, deleteHistories))
                .isInstanceOf(ExistTheOthersAnswerException.class)
                .hasMessage(ExistTheOthersAnswerException.EXIST_OTHERS_ANSWER);
        assertThat(deleteHistories).hasSize(0);
    }

    private static Stream<Arguments> provideTheOthersAnswers(){

        Answers answersObj1 = Answers.of(
                Lists.list(new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1_1"),
                        new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents1_2"),
                        new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1_3"))
        );

        Answers answersObj2 = Answers.of(
                Lists.list(new Answer(UserTest.JAVAJIGI, QuestionTest.Q2, "Answers Contents1_1"),
                        new Answer(UserTest.SANJIGI, QuestionTest.Q2, "Answers Contents1_2"))
        );

        return Stream.of(
                Arguments.of(UserTest.JAVAJIGI, answersObj1),
                Arguments.of(UserTest.SANJIGI, answersObj2)
        );
    }

    @ParameterizedTest
    @DisplayName("답변 정상 삭제 테스트")
    @MethodSource("provideAnswers")
    void deleteAnswersTest(User user, Answers answers) throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        answers.delete(user, deleteHistories);
        assertThat(deleteHistories).hasSize(answers.countAnswers());
        assertThat(deleteHistories).extracting("contentType", ContentType.class).contains(ContentType.ANSWER);
    }

    private static Stream<Arguments> provideAnswers(){

        Answers answersObj1 = Answers.of(
                Lists.list(new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1_1"),
                        new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1_2"),
                        new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1_3"))
        );

        Answers answersObj2 = Answers.of(
                Lists.list(new Answer(UserTest.SANJIGI, QuestionTest.Q2, "Answers Contents1_1"),
                        new Answer(UserTest.SANJIGI, QuestionTest.Q2, "Answers Contents1_2"))
        );

        return Stream.of(
                Arguments.of(UserTest.JAVAJIGI, answersObj1),
                Arguments.of(UserTest.SANJIGI, answersObj2)
        );
    }
}
