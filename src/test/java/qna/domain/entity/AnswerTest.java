package qna.domain.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static qna.domain.testdata.TestData.JAVAJIGI;
import static qna.domain.testdata.TestData.Q1;
import static qna.domain.testdata.TestData.SANJIGI;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import qna.domain.exception.CannotDeleteException;

@DisplayName("답변")
public class AnswerTest {

    public static Stream<Answer> javajigiAnswer() {
        return Stream.of(
            new Answer(JAVAJIGI, Q1, "Answers Contents1")
        );
    }

    @DisplayName("[성공] 삭제")
    @ParameterizedTest
    @MethodSource("javajigiAnswer")
    public void delete(final Answer answer) {
        // given

        // when
        final DeleteHistory deleteHistory = answer.delete(JAVAJIGI);

        // then
        assertThat(answer.isDeleted()).isTrue();
        assertThat(deleteHistory).isNotNull();
    }

    @DisplayName("[실패] delete - 질문자 답변자 다름")
    @ParameterizedTest
    @MethodSource("javajigiAnswer")
    public void delete_differentWriter(final Answer answer) {
        // given

        // when
        assertThrows(CannotDeleteException.class, () -> answer.delete(SANJIGI));

        // then
        assertThat(answer.isDeleted()).isFalse();
    }
}
