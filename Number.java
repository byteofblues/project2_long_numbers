public class Number {
    private int number;
    private int digitsCount;


    public Number() {}


    public Number(String number) throws NullPointerException, IllegalArgumentException {
        isNull(number);
        isValidNumber(number);
        setIntValue(number);
        this.digitsCount = number.length();
    }
    

    private void isNull(String number) throws NullPointerException {
        if (number == null) 
            throw new NullPointerException("number cannot be null");
    }


    private void isValidNumber(String number) throws IllegalArgumentException {
        String digits = "[0-9]+";
            if ( !(number.matches(digits)) ) {
                throw new IllegalArgumentException("invalid number: the input string contains non-digit characters");
            }           
    }


    private void setIntValue(String number) {
        int parsedNum = Integer.parseInt(number);
        this.number = parsedNum;
    }


    // public int compareTo​(Number other) throws NullPointerException {
    //     return 0;
    // }


    @Override 
    public String toString() {
        return Integer.toString(this.number);
    }

}