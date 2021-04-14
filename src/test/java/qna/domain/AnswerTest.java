package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("응답 생성 테스트")
    @Test
    void created_생성테스트() {
        // when
        Answer expected = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        // then
        assertThat(A1).isEqualTo(expected);
    }

    @DisplayName("응답 삭제처리 테스트")
    @Test
    void deleted_답변_삭제테스트() {
        // when
        assertThat(A1.isDeleted()).isFalse();
        A1.deleteAnswers();
        // then
        assertThat(A1.isDeleted()).isTrue();
    }

    @DisplayName("응답 작성자 확인 테스트")
    @Test
    void isOwner_작성자_확인테스트() {
        assertThat(A1.isOwner(UserTest.JAVAJIGI)).isTrue();
    }

    @DisplayName("응답 작성자 조회 테스트")
    @Test
    void getWriter_작성자_조회테스트() {
        assertThat(A1.getWriter()).isEqualTo(UserTest.JAVAJIGI);
    }

    @DisplayName("응답 contents 확인 테스트")
    @Test
    void getContents_질문_조회테스트() {
        assertThat(A1.getContents()).isEqualTo("Answers Contents1");
    }

}
