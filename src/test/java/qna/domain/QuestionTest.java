package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);


    @DisplayName("질문을 삭제했을경우 답변도 같이 잘 삭제 되는지 확인")
    @ParameterizedTest
    @MethodSource("injectDeleteHistories")
    void throwNotOwnDelete(Question question, User writer, List<DeleteHistory> deleteHistories) {
        assertThatThrownBy(() -> question.deleteQuestion(writer))
                .isInstanceOf(CannotDeleteException.class);
    }

    private static Stream<Arguments> injectDeleteHistories() {

        Answer A1 = AnswerTest.A1;
        Answer A2 = AnswerTest.A2;
        Q1.addAnswer(A1);
        Q2.addAnswer(A2);
        return Stream.of(
                Arguments.arguments(Q1, UserTest.JAVAJIGI ,
                        Arrays.asList(
                                new DeleteHistory(ContentType.QUESTION , Q1.getId() , Q1.getWriter() , LocalDateTime.now()),
                                new DeleteHistory(ContentType.ANSWER , A1.getId() , A1.getWriter() , LocalDateTime.now())
                        )),
                Arguments.arguments(Q2, UserTest.SANJIGI ,
                        Arrays.asList(
                                new DeleteHistory(ContentType.QUESTION , Q2.getId() , Q2.getWriter() , LocalDateTime.now()),
                                new DeleteHistory(ContentType.ANSWER , A2.getId() , A2.getWriter() , LocalDateTime.now())
                        ))
        );
    }

    @DisplayName("질문의 작성자가 아닌 유저가 답변을 달았을때 질문을 삭제할 경우 익셉션 발생")
    @ParameterizedTest
    @MethodSource("injectNotOwn")
    void throwNotOwnDelete(Question question, User writer) {
        assertThatThrownBy(() -> question.deleteQuestion(writer))
                .isInstanceOf(CannotDeleteException.class);
    }

    private static Stream<Arguments> injectNotOwn() {
        Q1.addAnswer(AnswerTest.A1);
        Q1.addAnswer(AnswerTest.A2);
        Q2.addAnswer(AnswerTest.A1);
        Q2.addAnswer(AnswerTest.A2);
        return Stream.of(
                Arguments.arguments(Q1, UserTest.JAVAJIGI),
                Arguments.arguments(Q2, UserTest.SANJIGI)
        );
    }


    @ParameterizedTest
    @DisplayName("질문 삭제할때 질문한 작성자가 다를경우 삭제할수 없음")
    @MethodSource("injectQuestionNotWriter")
    void deleteSuccess(Question question, User writer) {
        assertThatThrownBy(() -> question.deleteQuestion(writer)).isInstanceOf(CannotDeleteException.class);
    }

    private static Stream<Arguments> injectQuestionNotWriter() {
        return Stream.of(
                Arguments.arguments(Q1, UserTest.SANJIGI),
                Arguments.arguments(Q2, UserTest.JAVAJIGI)
        );
    }
}
