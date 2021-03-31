package qna.domain;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.*;

@DisplayName("질문")
public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);
    
    @Test
    @DisplayName("제목 변경")
    public void setTitle() throws Exception {
        //given
        String title = "장비를 정지합니다. 아니 안되잖아?";

        //when
        Q1.setTitle(title);

        //then
        assertThat(Q1.getTitle()).isEqualTo(title);
    }

    @Test
    @DisplayName("삭제 여부 변경")
    public void setDeleted() throws Exception {
        //given
        boolean deleteTrue = true;
        boolean deleteFalse = true;

        //when
        Q1.setDeleted(deleteTrue);
        Q2.setDeleted(deleteFalse);

        //then
        assertThat(Q1.isDeleted()).isTrue();
        assertThat(Q2.isDeleted()).isFalse();
    }
}
