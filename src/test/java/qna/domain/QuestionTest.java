package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);
    public static final Question Q3 = new Question("title3", "contents3").writeBy(UserTest.JAVAJIGI);

    @DisplayName("질문 생성 테스트")
    @Test
    void created_질문_생성테스트() {
        // when
        Question expected = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        // then
        assertThat(Q1).isEqualTo(expected);
    }

    @DisplayName("질문 정보 확인 테스트")
    @Test
    void getInfo_질문_정보_확인테스트() {
        assertAll(
                () -> assertThat(Q1.contentTitle()).isEqualTo("title1"),
                () -> assertThat(Q1.writer()).isEqualTo(UserTest.JAVAJIGI),
                () -> assertThat(Q1.content()).isEqualTo("contents1")
        );
    }

    @DisplayName("질문 작성자 확인 테스트")
    @Test
    void isOwner_작성자_확인테스트() {
        assertThat(Q1.isOwner(UserTest.JAVAJIGI)).isTrue();
    }

    @DisplayName("질문 삭제 처리 테스트")
    @Test
    void deleted_삭제_처리테스트() {
        // when
        assertThat(Q1.isDeleted()).isFalse();
        Q1.deleteQuestion();
        // then
        assertThat(Q1.isDeleted()).isTrue();
    }

    @DisplayName("질문에 응답 추가 테스트")
    @Test
    void add_응답_추가테스트() {
        // given
        Answer answer = new Answer(UserTest.SANJIGI, QuestionTest.Q2, "Answer contents3");
        Q1.addAnswer(answer);
        // when
        List<Answer> answers = Q1.answersToQuestion();
        Answer item = answers.get(0);
        // then
        assertAll(
                () -> assertThat(item.writer()).isEqualTo(UserTest.SANJIGI),
                () -> assertThat(item.contents()).isEqualTo("Answer contents3")
        );
    }
}
