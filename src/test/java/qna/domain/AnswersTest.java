package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AnswersTest {
    public static final Answers ANSWERS = new Answers(List.of(AnswerTest.A1, AnswerTest.A2));

    @Test
    @DisplayName("삭제 처리가 정상적으로 되는지 확인")
    void delete() {
        List<DeleteHistory> actualHistory = ANSWERS.delete();
        List<DeleteHistory> expectHistory = List.of(AnswerTest.A1_DELETE_HISOTRY, AnswerTest.A2_DELETE_HISOTRY);

        assertThat(AnswerTest.A1.isDeleted()).isTrue();
        assertThat(AnswerTest.A2.isDeleted()).isTrue();
        assertThat(actualHistory).isEqualTo(expectHistory);
    }

    @Test
    @DisplayName("답변 추가가 정상적으로 되는지 확인")
    void add() {
        Answers actual = new Answers(new ArrayList<>());
        actual.add(AnswerTest.A1);
        actual.add(AnswerTest.A2);

        assertThat(actual).isEqualTo(ANSWERS);
    }

    @Test
    @DisplayName("모든 답변이 하나의 유저에 의해 생성됐는지 확인")
    void isAllOwner() {
        Answers allOwner = new Answers(new ArrayList<>());
        allOwner.add(AnswerTest.A1);

        assertThat(allOwner.isAllOwner(AnswerTest.A1.getWriter())).isTrue();
        assertThat(ANSWERS.isAllOwner(AnswerTest.A1.getWriter())).isFalse();
    }
}