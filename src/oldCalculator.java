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
    int arrLength = parsed.length;
    if (arrLength % 2 == 0)
      {
        throw new Exception("Invalid expression format");
      }
    /*
    else if (arrLength == 1)
      {
        return new Fraction(parsed[0]);
      }
      */
    else
      {

        // Initializing the array of memory elements
        Fraction[] registers = new Fraction[8];
        for (int i = 0; i < 8; i++)
          registers[i] = Fraction.ZERO;

        // Initializing variables used later in the evaluation
        Fraction soFar = new Fraction(parsed[0]);
        String oper = new String("");
        Fraction arg = Fraction.ZERO;

        // Dealing with memory assignments
        if (parsed[0].charAt(0) == 'r')
          {
          char rChar = parsed[0].charAt(1);
          if (Character.isDigit(rChar))
            {
              int rNum = (int) Integer.valueOf(String.valueOf(rChar));
              //Check if the index after r is an integer between 0 and 7
              if (parsed[0].length() != 2 || rNum > 7)
                throw new Exception("Invalid call to memory register");
              else if (parsed[1] == "=")
                 registers[rNum] = evaluate(expr.substring(5));
            }
          else 
            throw new Exception ("Invalid call to memory register");//NEED TO WRITE THIS EXCEPTION
          }// if this is an assignment

        // Dealing with everything else
        for (int i = 1; i < parsed.length-1; i++)//look at operands
          {
            
                oper = parsed[i++];
                String temp = parsed[i];
                if (temp.charAt(0) == 'r')
                  arg = registers[temp.charAt(1)];//NEED TO CHECK IF VALID REGISTER
                else
                  arg = new Fraction(parsed[i]);
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
                    throw new Exception("Invalid operand");
                  }
              }// else (it's a number)
          }// for loop 
        return soFar;
      }// else (after throwing)

  }// eval0 (String)

}
