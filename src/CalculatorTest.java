import static org.junit.Assert.*;

import org.junit.Test;


public class CalculatorTest
{

  @Test
  public void evaluate3() throws Exception
  {
    assertTrue ("1", new Fraction("1/1").equals(Calculator.evaluate("1")));
  }
  @Test
  public void evaluate4() throws Exception
  {
    assertTrue ("1 + 1", new Fraction(2).equals(Calculator.evaluate("1 + 1")));
  }
  @Test
  public void evaluate5() throws Exception
  {
    assertTrue ("1 + 1 * 5", new Fraction(10).equals(Calculator.evaluate("1 + 1 * 5")));
  }
  @Test
  public void evaluate6() throws Exception
  {
    assertTrue ("1 + 1 * 5 / 2", new Fraction(5).equals(Calculator.evaluate("1 + 1 * 5 / 2")));
  }
  @Test
  public void evaluate7() throws Exception
  {
    assertTrue ("1 + 1 * 5 / 2 - 4", new Fraction(1).equals(Calculator.evaluate("1 + 1 * 5 / 2 - 4")));
  }
  @Test
  public void evaluate8() throws Exception
  {
    String[] expArr = {"r0 = 1", "r0 + 1"};
    assertTrue ("r0 = 1; r0 + 1", new Fraction(2).equals(Calculator.evaluate(expArr)[1]));
  }
  @Test
  public void evaluate9() throws Exception
  {
    String[] expArr = {"r0 = 1", "r1 = r0 + 1","r1 + 1" };
    assertTrue ("r0 = 1, r1 = r0 + 1, r1 + 1", new Fraction(3).equals(Calculator.evaluate(expArr)[2]));
  }
}
