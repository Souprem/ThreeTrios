import java.util.Objects;

public class ThreeTriosCard implements Card {

  final String CARD_NAME;

  AttackValue north;
  AttackValue south;
  AttackValue west;
  AttackValue east;

  public ThreeTriosCard(String name, String north, String south, String east, String west){
    this.CARD_NAME = name;
    this.north = setAttackValue(north, this.north);
    this.south = setAttackValue(south, this.south);
    this.east = setAttackValue(east, this.east);
    this.west = setAttackValue(west, this.west);
  }

  private AttackValue setAttackValue(String newValue, AttackValue val) {
    switch (newValue) {
      case "1":
        val = AttackValue.ONE;
        break;
      case "2":
        val = AttackValue.TWO;
        break;
      case "3":
        val = AttackValue.THREE;
        break;
      case "4":
        val = AttackValue.FOUR;
        break;
      case "5":
        val = AttackValue.FIVE;
        break;
      case "6":
        val = AttackValue.SIX;
        break;
      case "7":
        val = AttackValue.SEVEN;
        break;
      case "8":
        val = AttackValue.EIGHT;
        break;
      case "9":
        val = AttackValue.NINE;
        break;
      case "A":
        val = AttackValue.TEN;
        break;
      default:
        throw new IllegalArgumentException("Attack values must be 1-9 or A");
    }
    return val;
  }


  @Override
  public AttackValue getNorth() {
    return this.north;
  }

  @Override
  public AttackValue getSouth() {
    return this.south;
  }

  @Override
  public AttackValue getWest() {
    return this.west;
  }

  @Override
  public AttackValue getEast() {
    return this.east;
  }


  @Override
  public boolean equals(Object other){
    if (!(other instanceof ThreeTriosCard)){
      return false;
    }
    if (!Objects.equals(this.CARD_NAME, ((ThreeTriosCard) other).CARD_NAME)){
      return false;
    }
    if (this.east != ((ThreeTriosCard) other).east){
      return false;
    }
    if (this.west != ((ThreeTriosCard) other).west){
      return false;
    }
    if (this.south != ((ThreeTriosCard) other).south){
      return false;
    }
    if (this.north != ((ThreeTriosCard) other).north){
      return false;
    }
    return true;
  }

  @Override
  public int hashCode(){
    return Objects.hash(CARD_NAME, north, south, west, east);
  }

}
