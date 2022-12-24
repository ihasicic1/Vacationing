package ba.unsa.etf.rpr.exceptions;

public class MyException extends Exception{

    public MyException(String msg, Exception reason){
        super(msg, reason);
    }

    public MyException(String msg){
        super(msg);
    }
}
