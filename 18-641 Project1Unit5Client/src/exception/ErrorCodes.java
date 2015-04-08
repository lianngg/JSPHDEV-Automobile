package exception;

/**
 * 
 * @author hsuantzl
 * Enumeration of exceptions
 */
public enum ErrorCodes {
    MISSING_PRICE(0, "Required price in text file is missing"),
    MISSING_OPTIONSET(1, "Required optionSet data is missing"),
    MISSING_OPTION(2, "Required option data is missing"),
    FILENAME_ERROR(3, "File name is missing or wrong"),
    NEGATIVE_BASEPRICE(4, "Base price cannot be negative");

    private int errno;
    private String msg;

    private ErrorCodes(int errno, String msg) {
      this.errno = errno;
      this.msg = msg;
    }

    public int getErrno() {
      return this.errno;
    }

    public String getMsg() {
      return this.msg;
    }
}
