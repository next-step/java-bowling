package qna.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteInfoTest {
    public static final DeleteInfo DINF1 = new DeleteInfo(ContentType.QUESTION, null, UserTest.JAVAJIGI);
    public static final DeleteInfo DINF2 = new DeleteInfo(ContentType.ANSWER, null, UserTest.JAVAJIGI);

    @Test
    void 삭제정보를생성() {
        assertThat(new DeleteInfo(QuestionTest.Q1, UserTest.JAVAJIGI)).isEqualTo(DINF1);
        assertThat(new DeleteInfo(AnswerTest.A1, UserTest.JAVAJIGI)).isEqualTo(DINF2);
    }
}
