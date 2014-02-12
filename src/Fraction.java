import java.math.BigInteger;

public class Fraction
{
  BigInteger numerator;
  BigInteger denominator;
  static Fraction ZERO = new Fraction(0);

  public Fraction(int num, int denom) throws Exception
  {
    if (denom != 0)
      {
        this.numerator = BigInteger.valueOf(num);
        this.denominator = BigInteger.valueOf(denom);
        this.simplify();
      }
    else
      {
        throw new Exception("Invalid Denominator");
      }
  }// Fraction (int, int)

  public Fraction(int num)
  {
    this.numerator = BigInteger.valueOf(num);
    this.denominator = BigInteger.ONE;
    this.simplify();
  }// Fraction (int)

  public Fraction(BigInteger num, BigInteger denom) throws Exception
  {
    if (denom != BigInteger.ZERO)
      {
        this.numerator = num;
        this.denominator = denom;
        this.simplify();
      }
    else
      {
        throw new Exception("Invalid Denominator");
      }
  }// Fraction (BigInteger, BigInteger)

  public Fraction(BigInteger num)
  {
    this.numerator = num;
    this.denominator = BigInteger.ONE;
    this.simplify();
  }// Fraction (BigInteger)

  
  
  public Fraction(String input) throws Exception
  {
    String[] strArray = input.split("/");
    try 
    {
      this.numerator = new BigInteger(strArray[0]);
    }
    catch (Exception e)
    {
      throw new Exception ("Input must be a number");
    }
    if (strArray.length == 2)
      {
        if (strArray[1] == "0")
          {
            throw new Exception("Invalid Denominator");
          }
        else
          {
            try
            {
              this.denominator = new BigInteger(strArray[1]);
              this.simplify();
            }
            catch (Exception e)
            {
              throw new Exception ("Input must be a number");
            }
          }// nonzero denom
      }// length 2
    else if (strArray.length == 1)
      {
        this.denominator = BigInteger.ONE;
        this.simplify();
      }
    else
      {
        throw new Exception("Invalid input (multiple slashes)");
      }
  }// Fraction(String)

  private void simplify()
  {
    BigInteger gcd = this.denominator.gcd(this.numerator);
    this.denominator = this.denominator.divide(gcd);
    this.numerator = this.numerator.divide(gcd);
    this.cleanup();
  }// simplify()

  private void cleanup()
  {
    if (this.denominator.compareTo(BigInteger.ZERO) == -1)
      {
        this.numerator = this.numerator.negate();
        this.denominator = this.denominator.negate();
      }
    if (this.numerator.compareTo(BigInteger.ZERO) == 0)
    	  this.denominator = BigInteger.ONE;
  }// cleanup()

  public Fraction add(Fraction other)
  {
    Fraction sum = Fraction.ZERO;
    sum.numerator =
        (this.numerator.multiply(other.denominator)).add(other.numerator.multiply(this.denominator));
    sum.denominator = this.denominator.multiply(other.denominator);
    sum.simplify();
    return sum;
  }// add(Fraction)

  public Fraction subtract(Fraction other)
  {
    Fraction difference = Fraction.ZERO;
    difference.numerator =
        (this.numerator.multiply(other.denominator)).subtract(other.numerator.multiply(this.denominator));
    difference.denominator = this.denominator.multiply(other.denominator);
    difference.simplify();
    return difference;
  }// subtract(Fraction)

  public Fraction multiply(Fraction other)
  {
    Fraction product = Fraction.ZERO;
    product.numerator = this.numerator.multiply(other.numerator);
    product.denominator = this.denominator.multiply(other.denominator);
    product.simplify();
    return product;
  }// multiply(Fraction)

  public Fraction divide(Fraction other)
  {
    Fraction quotient = Fraction.ZERO;
    quotient.numerator = this.numerator.multiply(other.denominator);
    quotient.denominator = this.denominator.multiply(other.numerator);
    quotient.simplify();
    return quotient;
  }// divide(Fraction)

  public Fraction power(int pow)
  {
    Fraction exponent = Fraction.ZERO;
    exponent.denominator = this.denominator.pow(pow);
    exponent.numerator = this.numerator.pow(pow);
    exponent.simplify();
    return exponent;
  }// power(int)

  public String toString()
  {
    return (this.numerator.toString() + "/" + this.denominator.toString());
  }// toString()

  
  public Boolean equals(Fraction other)
  {
    //this.simplify();
    //other.simplify();
    if (this.denominator.equals(other.denominator)
        && this.numerator.equals(other.numerator))
      return true;
    else
      return false;
  }//equals(Fraction)
  /*
  public int hashCode()
  {
    //this.simplify();
    return this.denominator.hashCode() + this.numerator.hashCode(); 
  }//hashCode()
  */
  
}
