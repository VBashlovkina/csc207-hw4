import static org.junit.Assert.*;

import org.junit.Test;
//Testing exception throws inspired by the accepted answer to this question on stackoverflow.com
// http://stackoverflow.com/questions/156503/how-do-you-assert-that-a-certain-exception-is-thrown-in-junit-4-tests

public class CalculatorTest
{

  @Test
  public void evaluateTest()
    throws Exception
  {
    assertTrue("1", new Fraction("1/1").equals(Calculator.evaluate("1")));
    assertTrue("1 + 1", new Fraction(2).equals(Calculator.evaluate("1 + 1")));
    assertTrue("1 + 1 * 5",
               new Fraction(10).equals(Calculator.evaluate("1 + 1 * 5")));
    assertTrue("1 + 1 * 5 / 2",
               new Fraction(5).equals(Calculator.evaluate("1 + 1 * 5 / 2")));
    assertTrue("1 + 1 * 5 / 2 - 4",
               new Fraction(1).equals(Calculator.evaluate("1 + 1 * 5 / 2 - 4")));
    String[] expArr1 = { "r0 = 1", "r0 + 1"};
    assertTrue("r0 = 1; r0 + 1 (= 2)",
               new Fraction(2).equals(Calculator.evaluate(expArr1)[1]));
    String[] expArr2 = { "r0 = 1", "r1 = r0 + 1", "r1 * 2"};
    assertTrue("r0 = 1, r1 = r0 + 1, r1 * 2 (= 4)",
               new Fraction(4).equals(Calculator.evaluate(expArr2)[2]));
    String[] expArr3 = { "r0 = 1/2", "r1 = r0 + 1", "r1 * 3"};
    assertTrue("r0 = 1/2, r1 = r0 + 1, r1 * 3", 
               new Fraction("9/2").equals(Calculator.evaluate(expArr3)[2]));
    
  }// evaluateTest

  @Test(expected = Exception.class)
  public void oddNumberOfElements()
    throws Exception
  {
    Calculator.evaluate("1 + 5 *");
  }// oddNumberOfElements
  
  @Test(expected = Exception.class)
  public void invalidOperand()
    throws Exception
  {
    Calculator.evaluate("1 = 2");
  }// invalidOperand

  @Test(expected = InvalidMemoryRegisterException.class)
  public void invalidRegisterTest1()
    throws Exception
  {
    Calculator.evaluate("r8 = 67");
  }// invalidRegisterTest1

  @Test(expected = InvalidMemoryRegisterException.class)
  public void invalidRegisterTest2()
    throws Exception
  {
    String[] expArr = { "r0 = 1", "rr0 + 4" };
    Calculator.evaluate(expArr);
  }// invalidRegisterTest2

}//CalculatorTest
