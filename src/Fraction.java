import java.math.BigInteger;

/**
 * An implementation for Fractions
 * 
 * @author
 * @author Daniel Nanetti-Palacios
 * @author CSC207 2014S
 */

public class Fraction
{

  // +--------+-------------------------------------------------------
  // | Fields |
  // +--------+

  // The numerator of the function. Can be positive, negative, or zero.
  BigInteger numerator;

  // The denominator of the function. Must be positive.
  BigInteger denominator;

  // 0/1 fraction used to initialize fractions.
  static Fraction ZERO = new Fraction(0);

  // +--------------+-------------------------------------------------
  // | Constructors |
  // +--------------+

  // Builds new Fraction with numerator num and denominator denom.
  public Fraction(int num, int denom) throws Exception
  {
    if (denom != 0)
      {
        this.numerator = BigInteger.valueOf(num);
        this.denominator = BigInteger.valueOf(denom);
        this.simplify();
      }// if denominator is not 0
    else
      {
        throw new Exception("Invalid Denominator");
      }// if denominator is 0
  }// Fraction (int, int)

  // Builds new Fraction with numerator num and a denominator of 1.
  public Fraction(int num)
  {
    this.numerator = BigInteger.valueOf(num);
    this.denominator = BigInteger.ONE;
    this.simplify();
  }// Fraction (int)

  // Builds new Fraction with numerator num and denominator denom.
  public Fraction(BigInteger num, BigInteger denom) throws Exception
  {
    if (denom != BigInteger.ZERO)
      {
        this.numerator = num;
        this.denominator = denom;
        this.simplify();
      } // if denominator is not 0
    else
      {
        throw new Exception("Invalid Denominator");
      } // if denominator is 0
  }// Fraction (BigInteger, BigInteger)

  // Builds new Fraction with numerator num and a denominator of 1.
  public Fraction(BigInteger num)
  {
    this.numerator = num;
    this.denominator = BigInteger.ONE;
    this.simplify();
  }// Fraction (BigInteger)

  /**
   * Builds new Fraction from a string input. Input should be similar to
   * "num/denom" or just "num" Builds new Fraction with numerator num and
   * denominator demon, if input is just "num", then it builds a new Fraction
   * with numerator num and a denominator of 1.
   * 
   * @param String
   *          input
   * @throws Exception
   */
  public Fraction(String input) throws Exception
  {
    String[] strArray = input.split("/");
    try
      {
        this.numerator = new BigInteger(strArray[0]);
      } // try
    catch (Exception e)
      {
        throw new Exception("Input must be a number");
      } // catch (Exception e)
    if (strArray.length == 2)
      {
        if (strArray[1].compareTo("0") == 0)
          {
            throw new Exception("Invalid Denominator");
          } // if denominator is not 0
        else
          {
            try
              {
                this.denominator = new BigInteger(strArray[1]);
                this.simplify();
              } // try
            catch (Exception e)
              {
                throw new Exception("Input must be a number");
              } // catch (Exception e)
          } // nonzero denom
      } // length 2
    else if (strArray.length == 1)
      {
        this.denominator = BigInteger.ONE;
        this.simplify();
      }// String input only contained numerator
    else
      {
        throw new Exception("Invalid input (multiple slashes)");
      } // else
  }// Fraction(String)

  // +----------------+-----------------------------------------------
  // |Private Methods |
  // +----------------+

  /**
   * 
   * Simplifies a function to its most basic form. Utilizes cleanup method.
   * 
   */

  private void simplify()
  {
    BigInteger gcd = this.denominator.gcd(this.numerator);
    this.denominator = this.denominator.divide(gcd);
    this.numerator = this.numerator.divide(gcd);
    this.cleanup();
  }// simplify()

  /**
   * 
   * Prevents denominator from being negative and negates numerator is
   * denominator was negative. Changes all fractions with numerator 0 to 0/1 to
   * make them equal.
   * 
   */

  private void cleanup()
  {
    if (this.denominator.compareTo(BigInteger.ZERO) == -1)
      {
        this.numerator = this.numerator.negate();
        this.denominator = this.denominator.negate();
      } // if denominator is negative
    if (this.numerator.compareTo(BigInteger.ZERO) == 0)
      this.denominator = BigInteger.ONE;
    // if numerator is zero
  }// cleanup()

  // +---------+------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Add the fraction other to this fraction.
   */

  public Fraction add(Fraction other)
  {
    Fraction sum = Fraction.ZERO;
    sum.numerator =
        (this.numerator.multiply(other.denominator)).add(other.numerator.multiply(this.denominator));
    sum.denominator = this.denominator.multiply(other.denominator);
    sum.simplify();
    return sum;
  }// add(Fraction)

  /**
   * Subtract the fraction by the fraction other.
   */

  public Fraction subtract(Fraction other)
  {
    Fraction difference = Fraction.ZERO;
    difference.numerator =
        (this.numerator.multiply(other.denominator)).subtract(other.numerator.multiply(this.denominator));
    difference.denominator = this.denominator.multiply(other.denominator);
    difference.simplify();
    return difference;
  }// subtract(Fraction)

  /**
   * Multiply the fraction with the fraction other.
   */

  public Fraction multiply(Fraction other)
  {
    Fraction product = Fraction.ZERO;
    product.numerator = this.numerator.multiply(other.numerator);
    product.denominator = this.denominator.multiply(other.denominator);
    product.simplify();
    return product;
  }// multiply(Fraction)

  /**
   * Divide the fraction by the fraction other.
   */

  public Fraction divide(Fraction other)
  {
    Fraction quotient = Fraction.ZERO;
    quotient.numerator = this.numerator.multiply(other.denominator);
    quotient.denominator = this.denominator.multiply(other.numerator);
    quotient.simplify();
    return quotient;
  }// divide(Fraction)

  /**
   * Computes Fraction to the exponent pow
   */

  public Fraction power(int pow)
  {
    Fraction exponent = Fraction.ZERO;
    exponent.denominator = this.denominator.pow(pow);
    exponent.numerator = this.numerator.pow(pow);
    exponent.simplify();
    return exponent;
  }// power(int)

  /**
   * Converts the fraction into a string in the form "numerator/denominator". If
   * numerator is over a denominator 1, return only numerator.
   */

  public String toString()
  {
    if (!this.denominator.equals(BigInteger.ONE))
      return (this.numerator.toString() + "/" + this.denominator.toString());
    else
      return (this.numerator.toString());
  }// toString()

  /**
   * Checks if this fraction and fraction other are equal.
   */

  public Boolean equals(Fraction other)
  {
    if (this.denominator.equals(other.denominator)
        && this.numerator.equals(other.numerator))
      return true;
    // if the two numerators and denominators are equal
    else
      return false;
  }// equals(Fraction)

  /**
   * Produces the hash code for this function.
   */

  public int hashCode()
  {
    // this.simplify();
    return this.denominator.hashCode() + this.numerator.hashCode();
  }// hashCode()

}
