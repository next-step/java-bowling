package qna.domain.question;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;

@Embeddable
public class QuestionBody {
    public static final String DEFAULT_TITLE = "title";
    public static final String DEFAULT_CONTENTS = "contents";

    @Column(length = 100, nullable = false)
    private final String title;
    @Lob
    private final String contents;

    protected QuestionBody() {
        this(DEFAULT_TITLE, DEFAULT_CONTENTS);
    }

    public QuestionBody(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
