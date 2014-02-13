import java.io.PrintWriter;

/**
 * A user interface for the Calculator class.
 * 
 * @author
 * @author Daniel Nanetti-Palacios
 * @author CSC207 2014S
 */

public class Interface
{
 
  /**
   *
   * User interface that runs Calculator.java
   *
   * First expression entered follows "number operator number"
   *
   * Expressions that follow the first expression must be "operator number"
   *
   * Enter "clear" to reenter the first expression and clear memory
   *
   * Valid operators are + - * /
   *
   * Memory slots r0 - r7 To save an equation to memory, enter rX = expression
   *
   * Memory must be entered on first expression
   *
   */
  public static void main(String[] args)
    throws Exception
  {
    PrintWriter pen = new PrintWriter(System.out, true);
    java.io.InputStreamReader istream =
        new java.io.InputStreamReader(System.in);
    java.io.BufferedReader eyes = new java.io.BufferedReader(istream);

   
    // Initializing variables
    int counter = 0;
    String input = "";
    String firstElement = "";
    Fraction answer = Fraction.ZERO;
   
    // Read-Eval-Print Loop
    while (counter != 2)
      {
      
        input = eyes.readLine();
       
        if (input.compareToIgnoreCase("quit") == 0)
          {
            counter = 2;
            for (int i = 0; i < 8; i++)
              Calculator.evaluate("r" + i + " = 0");
          } // if user inputs quit
        else if (input.compareToIgnoreCase("clear") == 0)
          {
            counter = 0;
          } // else if user inputs clear
        else if (counter == 0)
          {
            if (input.charAt(0) == 'r' && input.charAt(3) == '=')
              {
                answer = Calculator.evaluate(input);
                pen.println("Memory stored");
                counter = 0;
              } // else if user saves expression into memory
            else
              {
                answer = Calculator.evaluate(input);
                pen.print(answer + " ");
                pen.flush();
                firstElement = answer.toString();
                counter++;
              } // else user does not want to save expression
          } // else if user enters first expression
        else
          {
            answer = Calculator.evaluate(firstElement + " " + input);
            pen.print(answer + " ");
            pen.flush();
            firstElement = answer.toString();
          } // else user enters expressions after first expression
     
      } // while(input != end)
   
  } // main
} // class Interface