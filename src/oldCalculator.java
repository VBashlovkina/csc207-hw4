
public class oldCalculator
{
  /**
   *validRegister returns the register index if it's valid 
   *otherwise it throws an exception
   */
  
  public static int validRegister (String r) throws InvalidMemoryRegisterException
  {
    char rChar = r.charAt(1);
    if (Character.isDigit(rChar))
      {
        int rNum = Integer.valueOf(String.valueOf(rChar));
        //Check if the index after r is an integer between 0 and 7
        if (r.length() != 2 || rNum > 7)
          throw new InvalidMemoryRegisterException("Available memory registers are r0 - r7");
        else 
          return rNum;
      } //if the next character is a digit
    else  
      throw new InvalidMemoryRegisterException();
    }//validRegister(String)
  
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
        return new Fraction(parsed[0]);// i don't think we need this
      }
    else
      {
     */
        // Initializing the array of memory elements
        Fraction[] registers = new Fraction[8];
        for (int i = 0; i < 8; i++)
          registers[i] = Fraction.ZERO;

        // Initializing variables used later in the evaluation
        Fraction soFar = new Fraction(parsed[0]);
        String oper = new String("");
        Fraction arg = Fraction.ZERO;

        // Dealing with memory assignments
        if (parsed[0].charAt(0) == 'r' && parsed[1] == "=")
                 registers[validRegister(parsed[0])] = evaluate(expr.substring(5));

        // Dealing with everything else
        for (int i = 1; i < parsed.length-1; i++)
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
      } //evaluate (String)

  }//Calculator


