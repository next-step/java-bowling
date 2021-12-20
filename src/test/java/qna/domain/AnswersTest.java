package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("질문에 대한 답변들 테스트")
class AnswersTest {
    public static Answers ANSWERS_A1 = Answers.from(Collections.singletonList(AnswerTest.A1));
    public static Answers ANSWERS_A1A2 = Answers.from(Arrays.asList(AnswerTest.A1, AnswerTest.A2));

    private Answers answers;

    @BeforeEach
    void setUp() {
        answers = new Answers();
    }

    @DisplayName("답변들 생성")
    @Test
    void createAnswersTest() {
        // then
        assertAll(
                () -> assertThat(answers).isNotNull(),
                () -> assertThat(answers.isEmpty()).isTrue()
        );
    }

    @DisplayName("정적 메서드를 이용한 답변 생성")
    @Test
    void fromTest() {
        // given
        List<Answer> answers = Arrays.asList(AnswerTest.A1, AnswerTest.A2);
        // when
        Answers actual = Answers.from(answers);
        // then
        assertAll(
                () -> assertThat(actual).isNotNull(),
                () -> assertThat(actual.getAnswers()).hasSize(answers.size())
        );
    }

    @DisplayName("정적 메서드를 이용한 답변 생성시, 잘못된 파라미터 예외 확인")
    @Test
    void checkExceptionToCreateAnswersWithInvalidParamTest() {
        // when & then
        assertThatIllegalArgumentException().isThrownBy(
                () -> Answers.from(null)
        );
    }

    @DisplayName("새로운 답변 추가")
    @Test
    void addTest() {
        // when
        Answers newAnswer1 = answers.add(AnswerTest.A1);
        Answers newAnswer2 = newAnswer1.add(AnswerTest.A2);
        // then
        assertAll(
                () -> assertThat(newAnswer1.getAnswers()).containsOnly(AnswerTest.A1),
                () -> assertThat(newAnswer2.getAnswers()).containsExactly(AnswerTest.A1, AnswerTest.A2)
        );
    }

    @DisplayName("잘못된 답변을 추가할 때 예외 확인")
    @Test
    void checkExceptionToAddInvalidAnswerTest() {
        // when & then
        assertThatIllegalArgumentException().isThrownBy(
                () -> answers.add(null)
        );
    }

    @DisplayName("답변들이 가지고 있는 답변들 정보 반환")
    @Test
    void getAnswersTest() {
        // when & then
        assertThat(answers.getAnswers()).isNotNull();
    }

    @DisplayName("답변들이 비어있는지 여부 확인")
    @Test
    void isEmptyTest() {
        // when & then
        assertThat(answers.isEmpty()).isTrue();
    }

    @DisplayName("답변들의 삭제 성공 여부 확인")
    @Test
    void deletableTrueTest() {
        // when & then
        assertThat(AnswersTest.ANSWERS_A1.deletable(AnswerTest.A1.getWriter())).isTrue();
    }

    @DisplayName("답변들의 삭제 실패 여부 확인")
    @Test
    void deletableFalseTest() {
        // when & then
        assertThat(AnswersTest.ANSWERS_A1A2.deletable(AnswerTest.A1.getWriter())).isFalse();
    }

    @DisplayName("답변들 삭제")
    @Test
    void deleteTest() throws CannotDeleteException {
        // given
        Answer answer = new Answer(101L, UserTest.JAVAJIGI, QuestionTest.Q1, "content1");
        Answers answers = Answers.from(Collections.singletonList(answer));
        // when
        List<DeleteHistory> histories = answers.delete(UserTest.JAVAJIGI).getDeleteHistories();
        // then
        assertThat(answers.isDeleted()).isTrue();
        assertThat(histories.stream().map(DeleteHistory::contentId)).containsExactly(answer.getId());
    }
}
