public class Operations {

    private static String binaryNumber = "";
    private static String hexaDecimal = "";
    private static String decimalNumber = "";
    public static int targetLength = 0;

    public static boolean hasError = false;

    //Task 1: Parse Input 

    public static void parseInput(String input) {
        if (input.length() < 3) {
            System.out.println(input + "=Decimal");
            decimalNumber = input;
            return;
        }
        String prefix = input.substring(0,2);

        if (prefix.equals("0b")) {
            System.out.println(input + "=Binary");
            binaryNumber = input;
            // binaryNumber = input.replaceAll("[()'\\[\\],]", "").trim();
    }
        else if (prefix.equals("0x")) {
            System.out.println(input + "=Hexadecimal");
            hexaDecimal = input;
            // hexaDecimal = input.replaceAll("[()'\\[\\],]", "").trim();
        }

        else {
            System.out.println(input + "=Decimal");
            decimalNumber = input;
            // decimalNumber = input.replaceAll("[()'\\[\\],]", "").trim();
        }

    }
        

    //Task 2: Identify numbering System
    
    public static boolean isBinary(String inputString) {
        if (inputString == null || inputString.isEmpty()) {
            return false;
        }
        if (inputString.startsWith("0b")) {
            inputString = inputString.substring(2);
            if (inputString.isEmpty()) {
                return false;
            }
        }
        
        for (char digit: inputString.toCharArray()) {
            if (digit != '1' && digit != '0') {
                return false;   
            }
        }
        return true;
    }

    public static boolean isHexadecimal(String inputString) {
        if (inputString == null || inputString.isEmpty()) {
            return false;
        }

        inputString = inputString.toLowerCase().trim();

        if (!inputString.startsWith("0x")) {
            return false;
        }

        inputString = inputString.substring(2);

        if (inputString.isEmpty()) {
            return false;
        }
        
        for (char c: inputString.toCharArray()) {
            if (!((c >='0' && c <= '9') || (c >= 'a' && c<= 'f'))) {
                return false;
            }
        } 
        return true;
    }

    public static boolean isDecimalNumber (String inputString) {
        if (inputString == null || inputString.isEmpty()) {
            return false; 
        }

        for (char number: inputString.toCharArray()) {
            if (!Character.isDigit(number)) {
            return false;
        }
    }
    return true;
}

    public static void binaryErrorCheck (String binaryString) {
        if (!isBinary(binaryString)) {
            System.out.println(binaryString + "=false");
            // System.exit(0);
            hasError = true;
        }
        else {
            System.out.println(binaryString + "=true");
            // hasError = false;
    }
}

    public static void hexaDecimalErrorCheck (String hexaString) {
        if (!isHexadecimal(hexaString)) {
            System.out.println(hexaString + "=false");
            // System.exit(0);
            hasError = true;
        }
        else {
            System.out.println(hexaString + "=true"); 
            // hasError = false;
    }
}

    public static void decimalErrorCheck(String decimalString) {
        if (!isDecimalNumber(decimalString)) {
            System.out.println(decimalString + "=false");
            // System.exit(0);
            hasError = true;
        }
        else {
            System.out.println(decimalString + "=true");
            // hasError = false;
        } 
    }
    public static void hasError() {
        if (hasError) {
            System.exit(0);
        }
    }

    //Task 4: Convert each input into corresponding numbering system */

    public static int convertToInteger(String input) {
        int number = 0;
        int length = input.length();

        for (int i = 0; i < length; i++) {
            number = number * 10 + ((int) input.charAt(i) - 48);
        }
        return number;
    }

    public static String decimalToBinary(int number) {
        if (number == 0) {
            System.out.print('0');
        }
        StringBuilder binaryString = new StringBuilder();

        while (number > 0) {
            int remainder = number % 2;
            binaryString.insert(0, remainder);
            number /= 2;
        }
        return (binaryString.toString());
    }

    public static String decimalToHexadecimal(int number) {
        if (number == 0) {
            return ("0x0");
        }
        StringBuilder hexa = new StringBuilder();
        char[] hexaChars = "0123456789abcdef".toCharArray();

        while (number > 0) {
            hexa.append(hexaChars[number % 16]);
            number /= 16;
        }
        return (hexa.reverse().toString());
    } 

    public static int binaryToDecimal(String binaryString) {
        int decimal = 0;
        int index = 0;
        int power = 0;
        int length = binaryString.length();

        for (index = length - 1; index >= 0; index--) {
            if (binaryString.charAt(index) == '1') {
                decimal += Math.pow(2, power);
            }
            power++;
        }
        return decimal;
    }

    public static String binaryToHexadecimal(String binaryString) {
        int decimal = binaryToDecimal(binaryString);
        return ("0x" + decimalToHexadecimal(decimal));
    }

    public static String hexaDecimalToBinary(String hexa) {
        int decimalValue = hexaDecimalToDecimal(hexa);
        String binaryValue = decimalToBinary(decimalValue);
        return binaryValue;
    }

    public static int hexaDecimalToDecimal(String hexa) {

        if (hexa.startsWith("0x")) {
            hexa = hexa.substring(2);
        }
        hexa = hexa.toLowerCase();
        int decimalValue = 0;

        for (int i = 0; i < hexa.length(); i++) {
            char c = hexa.charAt(i);
            int digit = 0;

            if (c >= '0' && c <= '9') {
                digit = c - '0';
            }
            else if (c >= 'a' && c <= 'f') {
                digit = c - 'a' + 10;
            }
            decimalValue = 16 * decimalValue + digit;
        }
        return decimalValue;
    }

    //Task 5: Ones complement

    public static String onesComplement(String binaryString) {
        int index = 0;
        int length = binaryString.length();
        StringBuilder onesComplement = new StringBuilder();

        for (index = 0; index < length; index++) {
            if (binaryString.charAt(index) == '1') {
                onesComplement.append('0');
            }
            if (binaryString.charAt(index) == '0') {
                onesComplement.append('1');
        }
    }
        return onesComplement.toString();
    }

    //Task 6: Twos complement

    public static String twosComplement(String binaryString) {
        String onesComplement = onesComplement(binaryString);
        StringBuilder twosComplementBuilder = new StringBuilder(onesComplement);
        int carry = 1;

        for (int i = twosComplementBuilder.length() - 1; i >= 0; i--) {
            if (twosComplementBuilder.charAt(i) == '1') {
                twosComplementBuilder.setCharAt(i, '0');
            }
            else {
                twosComplementBuilder.setCharAt(i, '1');
                carry = 0;
                break;
            }
        }
        if (carry == 1) {
            twosComplementBuilder.insert(0, '1');
        }
        return twosComplementBuilder.toString();
    }
    
    //Task 7: Bitwise OR, AND, and XOR

    //Helper method to add leading zeroes to make strings the same length
        
    public static String lineUpPlaceValues(String input, int targetLength) {
        int zeroesToAdd = targetLength - input.length();
        StringBuilder linedUpString = new StringBuilder();

        for (int i = 0; i < zeroesToAdd; i++) {
            linedUpString.append('0');
        }
        return linedUpString.append(input).toString();
    }

    public static String bitwiseOR(String binary1, String binary2, String binary3) {
        StringBuilder orString = new StringBuilder();

        for (int i = 0; i < binary1.length(); i++) {
            char b1 = binary1.charAt(i);
            char b2 = binary2.charAt(i);
            char b3 = binary3.charAt(i);

            if (b1 == '0' && b2 == '0' && b3 == '0') {
                orString.append('0');
            }
            else {
                orString.append('1');
            }
            }
        return orString.toString();
    }

    public static String bitwiseAND(String binary1, String binary2, String binary3) {
        StringBuilder andString = new StringBuilder(); {

        for (int i = 0; i < binary1.length(); i++) {
            char b1 = binary1.charAt(i);
            char b2 = binary2.charAt(i);
            char b3 = binary3.charAt(i);

            if (b1 == '1' && b2 == '1' && b3 == '1') {
                andString.append('1');
            }
            else {
                andString.append('0');
            }
        }
        return andString.toString();
    }
}

    public static String bitwiseXOR(String binary1, String binary2, String binary3) {
        StringBuilder xorString = new StringBuilder();
        StringBuilder xorStringFinal = new StringBuilder();

        for (int i = 0; i <= binary1.length()-1; i++) {
            char b1 = binary1.charAt(i);
            char b2 = binary2.charAt(i);

            if ((b1 == '1' && b2 == '0') || (b1 == '0' && b2 == '1')) {
                xorString.append('1');
            }
            else {
                xorString.append('0');
            }
        }

        for (int j = 0; j <= binary1.length()-1; j++) {
            char firstXor = xorString.charAt(j);
            char b3 = binary3.charAt(j);

            if ((firstXor == '1' && b3 == '0') || (b3 == '1' && firstXor == '0')) {
                xorStringFinal.append('1');
            }
            else {
                xorStringFinal.append('0');
            }
        }
        return xorStringFinal.toString();
    }

    //Task 8: Left and Right shifts

        public static String rightShift(String binaryString) {
            if (binaryString.length() < 3) {
                return "0";
            }
            return binaryString.substring(0,binaryString.length() - 2);
        }

        public static String leftShift(String binaryString) {
            return binaryString + "00";
        }
        
    public static void main(String[] args) {

        //Task 1: Parse 3 arguments

        System.out.println("Task 1");
        if (args.length != 3) {
            System.out.print("Incorrect number of arguments have been provided. Program Terminating! \n");
            System.exit(0);
        }
        else {
            System.out.print("Correct number of arguments given.\n");
        }
    
        System.out.print("\n");

        //Task 2: Identify numbering system

        System.out.println("Task 2");
        parseInput(args[0]);
        parseInput(args[1]);
        parseInput(args[2]);

        System.out.print("\n");

        //Task 3: Error Checking

        System.out.println("Task 3");
        binaryErrorCheck(binaryNumber);
        decimalErrorCheck(decimalNumber);
        hexaDecimalErrorCheck(hexaDecimal);

        hasError();

        System.out.print("\n");

        //Task 4: Convert each input into other numbering systems

        System.out.println("Task 4");

        int decimalInt = convertToInteger(decimalNumber);
        String decimalToBinary = decimalToBinary(decimalInt);
        String decimalToHexaDecimal = decimalToHexadecimal(decimalInt);

        int binaryToDecimal = binaryToDecimal(binaryNumber);
        String binaryToHexa = binaryToHexadecimal(binaryNumber);

        String hexadecimalToBinary = hexaDecimalToBinary(hexaDecimal);
        int hexadecimalToDecimal = hexaDecimalToDecimal(hexaDecimal);

        String binaryWithoutPrefix = binaryNumber.substring(2);

        System.out.println("Start=" + decimalInt + ",Binary=0b" + decimalToBinary + ",Decimal=" + decimalInt + ",Hexadecimal=0x" + decimalToHexaDecimal);
        System.out.println("Start=" + binaryNumber + ",Binary=" + binaryNumber + ",Decimal=" + binaryToDecimal + ",Hexidecimal=" + binaryToHexa);
        System.out.println("Start=" + hexaDecimal + ",Binary=0b" + hexadecimalToBinary + ",Decimal=" + hexadecimalToDecimal + ",Hexidecimal=" + hexaDecimal);

        System.out.print("\n");

        //Task 5: 1s complement 

        System.out.println("Task 5");
        System.out.println(decimalNumber + "=" + decimalToBinary + "=>" + onesComplement(decimalToBinary));
        System.out.println(binaryNumber + "=" + binaryWithoutPrefix + "=>" + onesComplement(binaryWithoutPrefix));
        System.out.println(hexaDecimal + "=" + hexadecimalToBinary + "=>" + onesComplement(hexadecimalToBinary));

        System.out.print("\n");

        //Task 6: Twos complement

        System.out.println("Task 6");
        System.out.println(decimalNumber + "=" + decimalToBinary + "=>" + twosComplement(decimalToBinary));
        System.out.println(binaryNumber + "=" + binaryWithoutPrefix + "=>" + twosComplement(binaryWithoutPrefix));
        System.out.println(hexaDecimal + "=" + hexadecimalToBinary + "=>" + twosComplement(hexadecimalToBinary));

        System.out.print("\n");

        //Task 7: Bitwise OR, AND, and XOR 

        System.out.println("Task 7");

        int longestBinaryString = Math.max(decimalToBinary.length(), 
                                  Math.max(binaryWithoutPrefix.length(), hexadecimalToBinary.length()));

        String linedUpDecimal = lineUpPlaceValues(decimalToBinary, longestBinaryString);
        String linedUpBinary = lineUpPlaceValues(binaryWithoutPrefix, longestBinaryString);
        String linedUpHexa = lineUpPlaceValues(hexadecimalToBinary, longestBinaryString);

        System.out.println(decimalToBinary + "|" + binaryWithoutPrefix + "|" + hexadecimalToBinary + "=" + bitwiseOR(linedUpBinary, linedUpDecimal, linedUpHexa));
        System.out.println(decimalToBinary + "&" + binaryWithoutPrefix + "&" + hexadecimalToBinary + "=" + bitwiseAND(linedUpBinary, linedUpDecimal, linedUpHexa));
        System.out.println(decimalToBinary + "^" + binaryWithoutPrefix + "^" + hexadecimalToBinary + "=" + bitwiseXOR(linedUpBinary, linedUpDecimal, linedUpHexa));
    
        System.out.print("\n");

        //Task 8: Left and right shift

        System.out.println("Task 8");
        System.out.println(decimalToBinary + "<<2=" + leftShift(decimalToBinary) + "," + decimalToBinary + ">>2=" + rightShift(decimalToBinary));
        System.out.println(binaryWithoutPrefix + "<<2=" + leftShift(binaryWithoutPrefix) + "," + binaryWithoutPrefix + ">>2=" + rightShift(binaryWithoutPrefix));
        System.out.println(hexadecimalToBinary + "<<2=" + leftShift(hexadecimalToBinary) + "," + hexadecimalToBinary + ">>2=" + rightShift(hexadecimalToBinary));
        }

    }

    

