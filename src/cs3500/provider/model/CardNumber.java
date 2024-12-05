package cs3500.provider.model;

/**
    * Represents the number of a card.
    */
public enum CardNumber {
  ONE(1, "1"),
  TWO(2, "2"),
  THREE(3, "3"),
  FOUR(4, "4"),
  FIVE(5, "5"),
  SIX(6, "6"),
  SEVEN(7, "7"),
  EIGHT(8, "8"),
  NINE(9, "9"),
  A(10, "A");

  private int number;
  private String str;

  /**
   * Retrieves the card number associated with the given string.
   *
   * @param cardNum The string representation of the card number.
   * @return The card number associated with the given string.
   */
  public static CardNumber getCardNumber(String cardNum) {
    try {
      int number = Integer.valueOf(cardNum);
      CardNumber[] var2 = values();
      int var3 = var2.length;

      for (int var4 = 0; var4 < var3; ++var4) {
        CardNumber cardNumber = var2[var4];
        if (cardNumber.number == number) {
          return cardNumber;
        }
      }

      throw new IllegalArgumentException("No such card number: " + number);
    } catch (NumberFormatException var6) {
      return A;
    }
  }

  public String toString() {
    return this.str;
  }

  public int getNumber() {
    return this.number;
  }

  private CardNumber(int number, String str) {
    this.number = number;
    this.str = str;
  }
}