package Model;

/**
 * Represents an attack value from 1-10
 * where 10 is represented as the hexidecimal A.
 */
public enum AttackValue {
  ONE(1),
  TWO(2),
  THREE(3),
  FOUR(4),
  FIVE(5),
  SIX(6),
  SEVEN(7),
  EIGHT(8),
  NINE(9),
  TEN(10);

  public final long numericValue;
  public final String stringValue;

  AttackValue(int num) {
    this.numericValue = num;
    if (num == 10) {
      this.stringValue = "A";
    } else {
      this.stringValue = String.valueOf(num);
    }
  }


}
