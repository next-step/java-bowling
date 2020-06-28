package bowling;

public class KnockDownNumber {

  private Integer value;

  public KnockDownNumber() {
  }

  public KnockDownNumber(Integer value) {
    this.value = value;
  }

  public int getIntValue() {
    return value == null ? 0 : value;
  }

  public boolean isNull() {
    return value == null;
  }

  @Override
  public String toString() {
    return "NumberOfKnockedDown{" +
        "value=" + value +
        '}';
  }
}
