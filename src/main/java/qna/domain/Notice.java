package qna.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;
import java.util.Objects;

@Embeddable
public class Notice {
    @Column(length = 100, nullable = false)
    private String title;

    @Lob
    private String contents;

    protected Notice() {
    }

    private Notice(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public static Notice create(String title, String contents) {
        return new Notice(title, contents);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notice notice = (Notice) o;
        return Objects.equals(title, notice.title) && Objects.equals(contents, notice.contents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, contents);
    }

    @Override
    public String toString() {
        return "Notice{" +
                "title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                '}';
    }
}
