
public class InvalidMemoryRegisterException extends Exception
  {
  private static final long serialVersionUID = 1L;

    public InvalidMemoryRegisterException() 
    {
      super();
    } // InvalidMemoryRegisterException()

    public InvalidMemoryRegisterException(String reason) 
    {
      super(reason);
    } // InvalidMemoryRegisterException
}
