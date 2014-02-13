public class Calculator
{
  // Array of memory registers
  static Fraction[] registers = new Fraction[8];

  /**
   * Determines the validity of the register reference, returns the register
   * index if it's valid, throws InvalidMemoryRegisterException otherwise.
   * 
   * @param String
   *          str, the parsed string containing reference to register
   * 
   * @pre str starts with 'r'
   * @post register index is returned
   */

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
                                                   "Tried to access r"
                                                       + rNum
                                                       + ". Available memory registers are r0 - r7");
        else
          return rNum;
      } // if the next character is a digit
    else
      throw new InvalidMemoryRegisterException("Tried to access " + r
                                               + ", which is invalid");
  }// validRegister(String)

  /**
   * Computes simple operations including addition, subtraction, multiplication,
   * division on integers and fractions
   * 
   * @param String
   *          expr, the expression to evaluate
   * @return the value of the expression
   * @pre expr is non null. It consists exclusively of sequences of digits
   *      (integers or fraction in the format a/b where b!=0) and the operators
   *      +, -, * or /. Numbers and operators are separated by a space. Every
   *      other element is an operator. The first and the last element of the
   *      expression are numbers
   * @post operations are done in the order in which they appear. The value of
   *       the expression is returned
   */

  public static Fraction evaluate(String expr)
    throws Exception
  {
    String[] parsed = expr.split(" ");
    int arrLength = parsed.length;
    if (arrLength % 2 == 0)
      throw new Exception(
                          "Invalid expression format: must contain an odd number of elements");

    // Initializing variables used later in the evaluation
    Fraction soFar = Fraction.ZERO;
    String oper = new String("");
    Fraction arg = Fraction.ZERO;

    // Dealing with memory assignments
    if (parsed[0].charAt(0) == 'r')
      {
        int index = validRegister(parsed[0]);
        if (parsed[1].compareTo("=") == 0)
          {
            // recurse on the rest of the expression
            registers[index] = evaluate(expr.substring(5));
            return registers[index];
          }
        else
          {
            parsed[0] = registers[index].toString();
          }
      }
    // Dealing with non-assignment expressions
    soFar = new Fraction(parsed[0]);
    for (int i = 1; i < parsed.length - 1; i++)
      {
        oper = parsed[i++];
        String temp = parsed[i];
        if (temp.charAt(0) == 'r')
          // if it's a reference to a register, retrieve it
          arg = registers[validRegister(parsed[i])];
        else
          // otherwise it's a number
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
            throw new Exception("Invalid operand " + oper);
          }
      }// evaluating for loop

    return soFar;
  }// evaluate (String)

  // needs comments, i can do it later
  public static Fraction[] evaluate(String[] expressions)
    throws Exception
  {
    Fraction[] answers = new Fraction[expressions.length];

    for (int i = 0; i < expressions.length; i++)
      {
        answers[i] = evaluate(expressions[i]);
      }
    return answers;
  }// evaluate (String[])

}// Calculator

