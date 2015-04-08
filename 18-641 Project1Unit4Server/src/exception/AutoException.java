package exception;

import model.Automobile;
/**
 * 
 * @author hsuantzl
 *
 */
public class AutoException extends Exception{
    private static final long serialVersionUID = 7542178280497278975L;
    private int errno;
    private String errorMsg;
    private String errorInfo;

    public AutoException(ErrorCodes code) {
        this.errno = code.getErrno();
        this.errorMsg = code.getMsg();
    }
    public AutoException(int errno) {
        this.errno = errno;
        this.errorMsg = "";
    }

    public int getErrorNumber() {
      return errno;
    }

    public String getErrorMsg() {
      return errorMsg;
    }
    
    public String getErrorInfo() {
        return errorInfo;
    }
    
    public void setErrorInfo(String info) {
        this.errorInfo = info;
    }
    
    /* Try to fix the error if possible with the error number */
    public void fix(AutoException e, Automobile automobile) {
        FixExceptionsHelper fix = new FixExceptionsHelper();
        int errno = e.getErrorNumber();
        switch(errno) {
            case 0:
                fix.fix0(errno);
                break;
            case 1:
                fix.fix1(errno);
                break;
            case 2:
                fix.fix2(errno);
                break;
            case 3:
                fix.fix3(errno, e.getErrorInfo());
                break;
            case 4:
                fix.fix4(errno, automobile);
                break;
        }
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("errno=");
        builder.append(errno);
        builder.append(", errorMsg: \"");
        builder.append(errorMsg);
        builder.append("\"");
        return builder.toString();
    }
    
    public void print() {
        System.out.println(this.toString());
    }
    
}
