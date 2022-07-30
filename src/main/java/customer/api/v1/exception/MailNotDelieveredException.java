package customer.api.v1.exception;

public class MailNotDelieveredException extends RuntimeException{
    public MailNotDelieveredException(String msg){
        super(msg);
    }
    public MailNotDelieveredException(){}

}
