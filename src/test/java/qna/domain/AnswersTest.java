package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import qna.CannotDeleteAnswersException;
import qna.CannotDeleteException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AnswersTest {

    private static Stream<Arguments> provideForDeleteAnswers() {
        return Stream.of(
                Arguments.of(
                        UserTest.JAVAJIGI,
                        Arrays.asList(
                                new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "answer1 by javajigi"),
                                new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "answer2 by javajigi"),
                                new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "answer3 by javajigi")
                        ),
                        Arrays.asList(
                                new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "answer1 by javajigi").delete(),
                                new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "answer2 by javajigi").delete(),
                                new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "answer3 by javajigi").delete()
                        )
                ),
                Arguments.of(
                        UserTest.SANJIGI,
                        Arrays.asList(
                                new Answer(UserTest.SANJIGI, QuestionTest.Q2, "answer1 by sanjigi"),
                                new Answer(UserTest.SANJIGI, QuestionTest.Q2, "answer2 by sanjigi")
                        ),
                        Arrays.asList(
                                new Answer(UserTest.SANJIGI, QuestionTest.Q2, "answer1 by sanjigi").delete(),
                                new Answer(UserTest.SANJIGI, QuestionTest.Q2, "answer2 by sanjigi").delete()
                        )
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideForDeleteAnswers")
    @DisplayName("answers 삭제하기")
    void deleteBy_writer(User writer, List<Answer> answerList, List<DeleteHistory> expectedList) throws CannotDeleteException {
        // given
        Answers answers = new Answers(answerList);
        DeleteHistories expected = new DeleteHistories(expectedList);

        // when
        DeleteHistories result = answers.deleteBy(writer);

        // then
        assertThat(result).isEqualTo(expected);

    }

    @Test
    @DisplayName("answers 삭제하기 실패 : 로그인한 유저가 아닌 답변 작성자가 있는 경우")
    void deleteBy_other_user() {
        // given
        Answer answer1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "answer1 by javajigi");
        Answer answer2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "answer1 by sanjigi");
        Answers answers =  new Answers(Arrays.asList(answer1, answer2));

        // when
        assertThatThrownBy(() -> { answers.deleteBy(UserTest.JAVAJIGI); })
                .isInstanceOf(CannotDeleteAnswersException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");

    }

}
