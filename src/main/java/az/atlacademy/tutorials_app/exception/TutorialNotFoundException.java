package az.atlacademy.tutorials_app.exception;

public class TutorialNotFoundException extends RuntimeException
{
    public TutorialNotFoundException(String message)
    {
        super(message);
    }    
}
