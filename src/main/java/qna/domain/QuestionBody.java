package qna.domain;

import javax.persistence.*;
import java.util.Objects;

@Embeddable
public class QuestionBody {
    @Column(length = 100, nullable = false)
    private String title;

    @Lob
    private String contents;

    public QuestionBody() {
    }

    public QuestionBody(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionBody that = (QuestionBody) o;
        return Objects.equals(title, that.title) && Objects.equals(contents, that.contents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, contents);
    }
}
