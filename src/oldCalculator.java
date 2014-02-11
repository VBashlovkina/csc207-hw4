import java.math.BigInteger;

public class oldCalculator
{
  /**
   * Computes simple operations including addition, subtraction, multiplication,
   * division and exponentiation.
   * 
   * @param String
   *          expr the expression to evaluate
   * @return the value of the expression
   * @pre expr is non null. It consists exclusively of sequences of digits
   *      (integers) and the operators +, -, *, / or ^ numbers and operators are
   *      separated by a space every other element is an operator the first and
   *      the last element of the expression are numbers
   * @post operations are done in the order in which they appear the value of
   *       the expression is returned
   */
  
  
  public static Fraction evaluate(String expr)
    throws Exception
  {
    
    String[] parsed = expr.split(" ");
    if (parsed.length % 2 == 0)
      {
        throw new Exception("Invalid expression");
      }
    else if (parsed.length == 1)
      {
        return new Fraction(parsed[0]);
      }
    else
      {
        
        //Initializing the array of memory elements
        Fraction[] r = new Fraction[8];
        for (int i = 0; i<8; i++)
          r[i] = Fraction.ZERO;
        
        Fraction soFar = new Fraction(parsed[0]);
        String oper = new String("");
        
        //Dealing with memory
        if (parsed[0].charAt(0) == 'r')
          {
          if (parsed[0].length() > 2 || parsed[0].charAt(1).toString().parseInt() > 7)
            throw new Exception ("Invalid memory element");
          else 
          }
        for (int i = 1; i < parsed.length; i++)
          {
            if (i % 2 != 0) // if i is odd, it's an operator
              {
                oper = parsed[i];
              }
            else
              {
                Fraction arg = new Fraction(parsed[i]);
                if (oper.compareTo("+") == 0)
                  soFar = soFar.add(arg);
                else if (oper.compareTo("-") == 0)
                  soFar = soFar.subtract(arg);
                else if (oper.compareTo("*") == 0)
                  soFar = soFar.multiply(arg);
                else if (oper.compareTo("/") == 0)
                  soFar = soFar.divide(arg);
                else
                  {
                    throw new Exception ("Invalid operand");
                  }
              }// else (it's a number)
          }// for
        return soFar;
      }//else (after throwing)

  }// eval0 (String)

}
