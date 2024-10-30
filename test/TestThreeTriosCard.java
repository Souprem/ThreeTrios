import Model.AttackValue;
import Model.Card;
import Model.ThreeTriosCard;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class TestThreeTriosCard {
  Card c1;
  Card c2;
  Card c3;
  Card c4;
  Card c5;


  @Before
  public void init() {
    c1 = new ThreeTriosCard("Card1", "1", "2", "3", "4");
    c3 = new ThreeTriosCard("Card3", "2", "2", "2", "2");
    c4 = new ThreeTriosCard("Card4", "5", "6", "7", "A");
    c5 = new ThreeTriosCard("Card5", "8", "9", "A", "A");
  }

  @Test
  public void testBasicCardInitializion() {
    Assert.assertEquals(AttackValue.ONE, c1.getNorth());
    Assert.assertEquals(AttackValue.TWO, c1.getSouth());
    Assert.assertEquals(AttackValue.THREE, c1.getEast());
    Assert.assertEquals(AttackValue.FOUR, c1.getWest());
  }

  @Test
  public void testNormalCardInitializion() {
    Assert.assertEquals(AttackValue.FIVE, c4.getNorth());
    Assert.assertEquals(AttackValue.SIX, c4.getSouth());
    Assert.assertEquals(AttackValue.SEVEN, c4.getEast());
    Assert.assertEquals(AttackValue.TEN, c4.getWest());
  }

  @Test
  public void testACardInitializion() {
    Assert.assertEquals(AttackValue.EIGHT, c5.getNorth());
    Assert.assertEquals(AttackValue.NINE, c5.getSouth());
    Assert.assertEquals(AttackValue.TEN, c5.getEast());
    Assert.assertEquals(AttackValue.TEN, c5.getWest());
  }

  @Test(expected = IllegalStateException.class)
  public void testBrokenCardInitializion() {
    c2 = new ThreeTriosCard("Card2", "0", "0", "0", "0");
  }
}
