package customer.api.v1.exception;

public class InvalidCustomerInputException extends RuntimeException{

    public InvalidCustomerInputException(String msg){
        super(msg);
    }

    public InvalidCustomerInputException(){

    }

}
