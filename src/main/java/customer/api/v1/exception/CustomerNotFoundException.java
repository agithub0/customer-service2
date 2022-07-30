package customer.api.v1.exception;

public class CustomerNotFoundException extends RuntimeException{

    public CustomerNotFoundException(String msg){
        super(msg);
    }
    public CustomerNotFoundException(){

    }

}
