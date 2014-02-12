import java.io.PrintWriter;

public class Calculator
{
  /**
   * validRegister returns the register index if it's valid otherwise it throws
   * an exception
   */

  static Fraction[] registers = new Fraction[8];

  public static int validRegister(String r)
    throws InvalidMemoryRegisterException
  {
    char rChar = r.charAt(1);
    if (Character.isDigit(rChar))
      {
        int rNum = Integer.valueOf(String.valueOf(rChar));
        // Check if the index after r is an integer between 0 and 7
        if (r.length() != 2 || rNum > 7)
          throw new InvalidMemoryRegisterException(
                                                   "Available memory registers are r0 - r7");
        else
          return rNum;
      } // if the next character is a digit
    else
      throw new InvalidMemoryRegisterException();
  }// validRegister(String)

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

    // Initializing variables used later in the evaluation
    Fraction soFar = Fraction.ZERO;
    String oper = new String("");
    Fraction arg = Fraction.ZERO;

    // Dealing with memory assignments
    if (parsed[0].charAt(0) == 'r')
      if (parsed[1].compareTo("=") == 0)
        {
          int index = validRegister(parsed[0]);
          registers[index] = evaluate(expr.substring(5));
          return registers[index];
        }
      else
        {
          parsed[0] = registers[validRegister(parsed[0])].toString();
        }

    // Dealing with everything else
    soFar = new Fraction(parsed[0]);
    for (int i = 1; i < parsed.length - 1; i++)
      {
        oper = parsed[i++];
        String temp = parsed[i];
        if (temp.charAt(0) == 'r')
          arg = registers[validRegister(parsed[i])];
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
      }// for loop

    return soFar;
  }// evaluate (String)

  public static Fraction[] evaluate(String[] expressions)
    throws Exception
  {
    Fraction[] answers = new Fraction[expressions.length];

    for (int i = 0; i < expressions.length; i++)
      {
        answers[i] = evaluate(expressions[i]);
      }
    return answers;
  }

  public static void main(String[] args)
    throws Exception
  {
    PrintWriter pen = new PrintWriter(System.out, true);
    Fraction test = evaluate("1 + 1");
    pen.println(test.toString());
    pen.println(test.equals(new Fraction("2/1")));
  }
}// Calculator

