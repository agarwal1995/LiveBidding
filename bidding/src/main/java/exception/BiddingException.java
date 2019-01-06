package exception;

public class BiddingException extends Exception {
	public final Long serialVersionUID = 124L;

	public BiddingException(String msg){
		super(msg);
	}
}
