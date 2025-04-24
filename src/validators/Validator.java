package validators;
import exceptions.InvalidException;

public abstract class Validator {
    public abstract void validate(String input) throws InvalidException;
}