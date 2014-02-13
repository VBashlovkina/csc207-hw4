import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Test;

public class FractionTest
{

  @Test
  public void fractionTest()
    throws Exception
  {
    for (int i = -10; i < 10; i++)
      {
        assertEquals("numtest", BigInteger.valueOf(i),
                     new Fraction(i).numerator);
        assertEquals("denomtest", BigInteger.ONE, new Fraction(i).denominator);
        assertEquals("numtest", BigInteger.valueOf(i),
                     new Fraction(i, 17).numerator);
        assertEquals("numtest",
                     BigInteger.valueOf(i),
                     new Fraction(BigInteger.valueOf(i), BigInteger.valueOf(17)).numerator);
        assertEquals("numtest", BigInteger.valueOf(i),
                     new Fraction(Integer.toString(i) + "/17").numerator);
        assertEquals("equaltest", true,
                     new Fraction((i * 2), 2).equals(new Fraction(i)));
        assertEquals("additiontest", BigInteger.valueOf(i + 1),
                     (new Fraction(i).add(new Fraction(1))).numerator);
        assertEquals("subtractiontest", BigInteger.valueOf(i - 1),
                     (new Fraction(i).subtract(new Fraction(1))).numerator);
        assertEquals("multiplicationtest", BigInteger.valueOf(i * 2),
                     (new Fraction(i).multiply(new Fraction(2)).numerator));
        assertEquals("divisiontest", BigInteger.valueOf(i),
                     (new Fraction(i * 2).divide(new Fraction(2)).numerator));
        assertEquals("toStringtest", (Integer.toString(i) + "/1"),
                     new Fraction(i * 2, 2).toString());
      }//for
  }//fractionTest
  

}
