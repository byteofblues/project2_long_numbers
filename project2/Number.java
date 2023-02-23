package project2;

public class Number {

    /** 
     * Define the internal Node class that holds a single digit 
     * integer value and a reference to the next node in the list.
     */
    private class Node {
        public int data;
        public Node next;

        public Node (int number) {
            this.data = number;
            this.next = null;
        }
    }   

    private Node head;
    private Node tail;
    private int digitCount;


    /**
     * Creates an empty Number object.  
     **/
    public Number() {
        this("");
    }


    /**
     * Creates a Number object with value represented by the string argument number. 
     * The number should consist of decimal digits only.
     * @param number - a string representation of the number
     * @throws NullPointerException - if number is null
     * @throws IllegalArgumentException - if number contains any illegal characters
     */
    public Number(String number) throws NullPointerException, IllegalArgumentException {
        this.digitCount = 0;
        createLinkedListFromNumber(number);
    }


    /**
     * Creates a linked list of digits from a string of digits. The digits are
     * stored in reverse order, with the least significant digit at the head of
     * the list and the most significant digit at the tail of the list.
     * @param number the string of digits to be converted to a linked list
     * @throws NullPointerException if number is null
     * @throws IllegalArgumentException if number contains non-digit characters
     */
    private void createLinkedListFromNumber(String number) throws NullPointerException, IllegalArgumentException {
        isNull(number);

        if (number.equals("")) {
            this.head = null;
            this.tail = null;
            return; 
        }

        int l = number.length();
        for (int i = l; i > 0; i--) {
            String digit = number.charAt(i - 1) + "";
            // check if character is a decimal digit
            isValidDigit(digit);
            // convert digit to int
            int parsedNum = Integer.parseInt(digit);
            appendNode(this, parsedNum);
        }
    }
    

    /**
     * Throws a NullPointerException with a message 
     * "number cannot be null" if the input object is null.
     * @param obj the element to check for null 
     * @throws NullPointerException if obj == null 
     */
    private void isNull(Object obj) throws NullPointerException {
        if (obj == null) 
            throw new NullPointerException("Number cannot be null");
    }


    /** 
     * Throws an IllegalArgumentException with a message when 
     * input string contains one or more non-digit characters.
     * @param number the string to check for digit characters
     * @throws IllegalArgumentException if the input string contains non-digit characters
     */
    private void isValidDigit(String number) throws IllegalArgumentException {
        String digits = "[0-9]+";
        if ( !(number.matches(digits)) ) 
            throw new IllegalArgumentException("Invalid number: the input string contains non-digit characters");          
    }


    /**
     * Returns the number of digits in this object.
     * @return the number of digits in this object
     */
    public int length() {
        return this.digitCount;
    }


    /**
     * Creates a new node with the specified integer value and appends it to the end of the linked list.
     * The value of the node represents the next least significant digit and it to be stored as the tail.
     * @param number the Number object representing the linked list to append the node to.
     * @param value the integer value to be stored in the new node.
     */
    private void appendNode(Number number, int value) {
        Node n = new Node(value);

        // least significant digit stored as head, most significant digit stored as tail
        if (number.head == null) {
            number.head = n;
            number.tail = n; 
        } else {
            number.tail.next = n;
            number.tail = n;
        }

        this.digitCount++;
    }


    /**
     * Computes the sum of this object with other. Returns the result 
     * in a new object. This object is not modified by call to add.
     * @param other - the value to be added to this object
     * @return a Number object whose value is equal to the sum of this object and other
     * @throws NullPointerException - if other is null
     */
    public Number add(Number other) throws NullPointerException {
        isNull(other);
        Number finalNumber = new Number();

        // add the number objects this(number method was called on) and other(number provided as argument)
        Node currentA = this.head;
        Node currentB = other.head;
        int sum = 0, remainder = 0;

        while ( (currentA != null) || (currentB != null)) {

            sum = remainder;
            if (currentA != null) sum += currentA.data;
            if (currentB != null) sum += currentB.data;

            if (sum > 9) remainder = 1;
                else remainder = 0;
            sum = sum % 10;
            appendNode(finalNumber, sum);

            // normal case where both currentA and currentB are not null
            if (currentA != null) currentA = currentA.next;
            if (currentB != null) currentB = currentB.next;
        }

        if (remainder > 0) appendNode(finalNumber, sum);
        return finalNumber;
    }



    /**
     * Computes the product of this object and a single digit digit. 
     * Returns the result in a new object. This object is not modified by call to multiplyByDigit.
     * @param digit - a single positive digit to be used for multiplication
     * @return a Number object whose value is equal to the product of this object and digit
     * @throws IllegalArgumentException - when digit is invalid (i.e., not a single digit or negative)
     */
    public Number multiplyByDigit​(int digit) throws IllegalArgumentException {
        // check if digit is a decimal digit
        isValidDigit(String.valueOf(digit));
        Number finalNumber = new Number();

        Node current = this.head;
        int sum = 0, remainder = 0;
        while (current != null) {
            // multiply digit starting from the ones place of this object
            sum = remainder + (current.data * digit);
            if (sum > 9) {
                remainder = (sum - (sum % 10)) / 10;
            } else remainder = 0;
            sum = sum % 10;
            appendNode(finalNumber, sum);

            // advance to next node in this object
            current = current.next;
        }

        if (remainder > 0) appendNode(finalNumber, remainder);
        return finalNumber;
    }


    /**
     * Computes the product of this object and other. Returns the result in a new object. 
     * This object is not modified by call to multiply.
     * @param other - the value to be multiplied by this object
     * @return a Number object whose value is equal to the product of this object and other
     * @throws NullPointerException - if other is null
     */
    public Number multiply(Number other) throws NullPointerException {
        isNull(other);
        Number finalNumber = new Number();
        
        Node currentB = other.head; // iterator for the digits in the multiplier -> other
        Number product = new Number();
        int digit, digitsPlace = 0; // maintain a counter to let us know what digits place of other we are at

        // continue while there are digits remaining in the multiplier
        while (currentB != null) {
            // parses a single digit from other object 
            digit = currentB.data;
            product = this.multiplyByDigit​(digit);
            addZeroNodeAtStart(product, digitsPlace);
            finalNumber = finalNumber.add(product); // maintain and update the current sum up to the digit being parsed
            digitsPlace++;

            // advance to next node in other object
            currentB = currentB.next;
        }

        return finalNumber;
    }


    /**
     * Prepend the specified number of zero nodes to the start of the linked list.
     * @param list the linked list to which the zero nodes should be added
     * @param count the number of zero nodes to add to the start of the list
     * @throws NullPointerException if the linked list is null
     */
    private void addZeroNodeAtStart(Number list, int count) throws NullPointerException {
        isNull(list); // Check if the linked list is null
        // Add the specified number of zero nodes to the start of the linked list
        for (int i = 0; i < count; i++) {
            Node newNode = new Node(0);
            newNode.next = list.head;
            list.head = newNode;
        }
    }


    /**
     * Reverses the order of characters in the input string.
     * @param str the string to be reversed.
     * @return the reversed string.
     */
    public String reverseString(String str) {
        String reversedStr = "";
        for (int i = str.length(); i > 0; i--) {
            reversedStr += str.charAt(i - 1);
        }

        return reversedStr;
    }


    /**
     * Returns the string representation of this object.
     * @return string representation of this object
     */
    @Override 
    public String toString() {
        String s = "";
        Node current = this.head;

        while (current != null) {
            s += current.data;
            current = current.next;
        }
        s = reverseString(s);
        return s;
    }


    /**
     * Determines if this object is equal to obj. Two Number objects are equal 
     * if all of their digits are the same and in the same order, and if they 
     * have the same number of digits. In other words, if the values 
     * represented by the two objects are the same.
     * @param obj - the object to be compared to this object
     * @return true if two objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this ) return true; 
        if ( (obj == null ) || !(obj instanceof Number) ) return false; 

        Number other = (Number) obj; 

        // make sure that they store the same type of elements 
        if ((this.head == null) && (other.head == null)) {
            return false;
        }

        //check if length of lists are the same 
        if ((this.digitCount != other.digitCount)) {
            return false;
        }

        // Compare corresponding pairs of digits sequentially
        Node nodeA = this.head;
        Node nodeB = other.head;
        while (nodeA != null) {
            if (nodeA.data != nodeB.data) {
                return false;
            }
            nodeA = nodeA.next;
            nodeB = nodeB.next;
        }

        return true;
    }


    /**
     * Compares this object with other for order. Returns a negative integer 
     * if this object's value is strictly smaller than the value of other. 
     * Returns a positive integer if this object's value is strictly greater 
     * than the value of other. Returns zero if two values are the same.
     * @param other - the object to be compared with this object
     * @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than other
     * @throws NullPointerException - if other is null
     */
    @Override
    public int compareTo​(Number other) throws NullPointerException {
        isNull(other);
        return 0;
    }


}