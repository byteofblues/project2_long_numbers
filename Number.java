public class Number {
    private Node head;
    private Node tail;
    private int digitsCount;


    public Number() {}


    public Number(String number) throws NullPointerException, IllegalArgumentException {
        createLinkedListFromNumber(number);
        this.digitsCount = 0;
    }
    

    private void isNull(String number) throws NullPointerException {
        if (number == null) 
            throw new NullPointerException("number cannot be null");
    }


    private void isValidDigit(String number) throws IllegalArgumentException {
        String digits = "[0-9]+";
        if ( !(number.matches(digits)) ) 
            throw new IllegalArgumentException("invalid number: the input string contains non-digit characters");          
    }


    private int setIntValue(String number) {
        int parsedNum = Integer.parseInt(number);
        return parsedNum;
    }
    

    private void createLinkedListFromNumber(String number) throws NullPointerException, IllegalArgumentException {
        // check if number is not null
        isNull(number);

        this.head = null;
        this.tail = null;
        // Node current = head;

        int l = number.length();
        for (int i = l; i > 0; i--) {
            String digit = number.charAt(i - 1) + "";
            // check if character is a decimal digit
            isValidDigit(digit);

            // create a new node starting from the least significant digit, or the ones digit
            // method for linked list implementation
            Node n = new Node(setIntValue(digit));

            // least significant digit stored as head
            // stores the first node as head
            if (this.head == null) {
                this.head = n;
                this.tail = n; 
            }

            // append the next least significant digit to the list
            else {
                this.tail.next = n;
                this.tail = n;
            }

            this.digitsCount++;
        }
    }

    /*
     * compares two Number objects, returns equal
     * if all the nodes in the linked list contain
     * the same integer value, in the same order
     */
    // @Override
    // public int compareTo​(Number other) throws NullPointerException {
    //     return 0;
    // }


    public String printNumber() {
        String s = "";
        Node current = this.head;

        while (current != null) {
            s += current.data;
            current = current.next;
        }

        s = reverseString(s);

        return s;
    }


    public String reverseString(String str) {
        String reversedStr = "";
        for (int i = str.length(); i > 0; i--) {
            reversedStr += str.charAt(i - 1);
        }

        return reversedStr;
    }


    @Override 
    public String toString() {
        // return Integer.toString(this.number);
        return printNumber();
    }


    /*
     * define the internal Node class, constructs
     * a Node that stores a single digit from its 
     * series of digits
     */
    private class Node {
        public int data;
        public Node next;

        public Node (int number) {
            this.data = number;
            this.next = null;
        }
    }   

}