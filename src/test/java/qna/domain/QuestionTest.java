package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import qna.CannotDeleteException;
import qna.NotAuthorizedDeleteException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    private static Stream<Arguments> provideForDeleteQuestion() {
        return Stream.of(
                Arguments.of(
                        UserTest.JAVAJIGI,
                        QuestionTest.Q1,
                        Arrays.asList(
                                new Answer(UserTest.JAVAJIGI, Q1, "answer1 by javajigi"),
                                new Answer(UserTest.JAVAJIGI, Q1, "answer2 by javajigi"),
                                new Answer(UserTest.JAVAJIGI, Q1, "answer3 by javajigi")
                        ),
                        Arrays.asList(
                                new DeleteHistory(ContentType.QUESTION, Q1.getId(), Q1.getWriter(), LocalDateTime.now()),
                                new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "answer1 by javajigi").delete(),
                                new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "answer2 by javajigi").delete(),
                                new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "answer3 by javajigi").delete()
                        )
                ),
                Arguments.of(
                        UserTest.SANJIGI,
                        QuestionTest.Q2,
                        Arrays.asList(
                                new Answer(UserTest.SANJIGI, Q2, "answer1 by sanjigi"),
                                new Answer(UserTest.SANJIGI, Q2, "answer2 by sanjigi")
                        ),
                        Arrays.asList(
                                new DeleteHistory(ContentType.QUESTION, Q2.getId(), Q2.getWriter(), LocalDateTime.now()),
                                new Answer(UserTest.SANJIGI, Q2, "answer1 by sanjigi").delete(),
                                new Answer(UserTest.SANJIGI, Q2, "answer2 by sanjigi").delete()
                        )
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideForDeleteQuestion")
    @DisplayName("Question 삭제하기")
    void deleteBy_writer(User writer, Question question, List<Answer> answerList, List<DeleteHistory> expectedList) throws CannotDeleteException {
        // given
        answerList.forEach(question::addAnswer);
        DeleteHistories expected = new DeleteHistories(expectedList);

        // when
        DeleteHistories result = question.deleteBy(writer);

        // then
        assertThat(result).isEqualTo(expected);

    }

    @Test
    @DisplayName("Question 삭제하기 실패 : 로그인한 유저랑 질문 작성자가 다른 경우")
    void deleteBy_other_user() {
        // given
        Question question = new Question("question title", "contents").writeBy(UserTest.JAVAJIGI);
        Answer answer1 = new Answer(UserTest.JAVAJIGI, question, "answer1 by javajigi");
        Answer answer2 = new Answer(UserTest.JAVAJIGI, question, "answer2 by javajigi");
        question.addAnswer(answer1);
        question.addAnswer(answer2);

        // when
        assertThatThrownBy(() -> { question.deleteBy(UserTest.SANJIGI); })
                .isInstanceOf(NotAuthorizedDeleteException.class)
                .hasMessage("질문을 삭제할 권한이 없습니다.");

    }

}
