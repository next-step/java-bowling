package qna.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ContentTypeTest {
    @Test
    void 글타입확인() {
        assertThat(ContentType.check(AnswerTest.A1)).isEqualTo(ContentType.ANSWER);
        assertThat(ContentType.check(QuestionTest.Q1)).isEqualTo(ContentType.QUESTION);
    }
}
