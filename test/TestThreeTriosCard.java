import cs3500.model.AttackValue;
import cs3500.model.Card;
import cs3500.model.ThreeTriosCard;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

/**
 * A class to test the ThreeTriosCard class.
 */
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

  @Test(expected = IllegalArgumentException.class)
  public void testCardInitZeroAttackValues() {
    c2 = new ThreeTriosCard("Card2", "0", "1", "1", "1");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCardInitEmptyAttackValue() {
    c2 = new ThreeTriosCard("Card2", "", "1", "1", "1");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCardInitNullName() {
    c2 = new ThreeTriosCard(null, "1", "1", "1", "1");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCardInitNullAttackValue() {
    c2 = new ThreeTriosCard("Card2", "1", "1", "1", null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCardInitEmptyName() {
    c2 = new ThreeTriosCard("", "1", "1", "1", "1");
  }

  @Test
  public void testAttackValueInit() {
    AttackValue a = AttackValue.TEN;
    Assert.assertEquals(a.numericValue, 10);
    Assert.assertEquals(a.stringValue, "A");
  }
}
