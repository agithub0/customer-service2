package customer.api.v1.exception;


public class UserException extends RuntimeException{

    public UserException(String msg){
        super(msg);
    }

    public UserException(){

    }

}
