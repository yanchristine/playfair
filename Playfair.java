/*
Christine Yan
Cybersecurity Fall 2020
Playfair Cipher
*/

public class Playfair {

    // Make the Playfair Cipher Table
    public static String[][] makeTable(String letters) {
        String[][] table = new String[5][5];
        int index = 0;
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                table[i][j] = letters.substring(index, index + 1);
                index++;
            }
        }
        return table;
    }

    // For Double Letters (Add X between)
    public static String doubles(String txt) {
        int len = txt.length();
        for (int i = 0; i < len - 1; i = i + 2) {
            if (txt.substring(i + 1, i + 2).equals(txt.substring(i, i + 1))) {
                txt = txt.substring(0, i + 1) + "X" + txt.substring(i + 1);
            }
        }
        return txt;
    }

    // Remove J and Replace with I instead
    public static String JtoI(String txt) {
        String newTxt = "";
        int len = txt.length();
        for (int i = 0; i < len; i++) {
            if (("J").equals(txt.substring(i, i + 1))) {
                newTxt = newTxt + "I";
            } else {
                newTxt = newTxt + txt.substring(i, i + 1);
            }
        }
        return newTxt;
    }

    // If odd string length, add "Z"
    public static String addZ(String txt) {
        int len = txt.length();
        if (len % 2 == 1) {
            txt = txt + "Z";
        }
        return txt;
    }
    
    // Make pairs by finding matching row and column
    public static int[] makePair(String character, String[][] table) {
        int[] pair = {0, 0};
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                if (character.equals(table[row][col])) {
                    pair[0] = row;
                    pair[1] = col;
                }
            }
        }
        return pair;
    }
    
    // Encode Helper Functions
    public static String verticalEncode(String pair, String[][] table) {
        String sub1 = pair.substring(0, 1);
        String sub2 = pair.substring(1);
        int[] pair1 = makePair(sub1, table);
        int[] pair2 = makePair(sub2, table);
        int row = pair1[0] + 1;
        if (row <= -1) {
            row = 4;
        }
        if (row >= 5) {
            row = 0;
        }
        String val1 = table[row][pair1[1]];
        String val2 = table[row][pair2[1]];
        String newVal = val1 + val2;
        return newVal;
    }

    public static String horizontalEncode(String pair, String[][] table) {
        String sub1 = pair.substring(0, 1);
        String sub2 = pair.substring(1);
        int[] pair1 = makePair(sub1, table);
        int[] pair2 = makePair(sub2, table);
        int column = pair1[1] + 1;
        if (column <= -1) {
            column = 4;
        }
        if (column >= 5) {
            column = 0;
        }
        String val1 = table[pair1[0]][column];
        String val2 = table[pair2[0]][column];
        String newVal = val1 + val2;
        return newVal;
    }
    
    // Decode Helper Functions
    public static String verticalDecode(String pair, String[][] table) {
        String sub1 = pair.substring(0, 1);
        String sub2 = pair.substring(1);
        int[] pair1 = makePair(sub1, table);
        int[] pair2 = makePair(sub2, table);
        int row = pair1[0] - 1;
        if (row <= -1) {
            row = 4;
        }
        if (row >= 5) {
            row = 0;
        }
        String val1 = table[row][pair1[1]];
        String val2 = table[row][pair2[1]];
        String newVal = val1 + val2;
        return newVal;
    }

    public static String horizontalDecode(String pair, String[][] table) {
        String sub1 = pair.substring(0, 1);
        String sub2 = pair.substring(1);
        int[] pair1 = makePair(sub1, table);
        int[] pair2 = makePair(sub2, table);
        int column = pair1[1] - 1;
        if (column <= -1) {
            column = 4;
        }
        if (column >= 5) {
            column = 0;
        }
        String val1 = table[pair1[0]][column];
        String val2 = table[pair2[0]][column];
        String newVal = val1 + val2;
        return newVal;
    }
    
    // Regular Helper
    public static String regular(String pair, String[][] table) {
        String sub1 = pair.substring(0, 1);
        String sub2 = pair.substring(1);
        int[] pair1 = makePair(sub1, table);
        int[] pair2 = makePair(sub2, table);

        String val1 = table[pair1[0]][pair2[1]];
        String val2 = table[pair2[0]][pair1[1]];
        String newVal = val1 + val2;
        return newVal;
    }
    
    // Main Encode Function
    public static String encode(String input, String[][] table) {
        String txt = JtoI(input);
        txt = doubles(txt);
        txt = addZ(txt);
        String output = "";
        
        int len = txt.length() - 1;
        int i = 0;
        while (i < len) {
            String char1 = "" + txt.substring(i, i + 1);
            String char2 = "" + txt.substring(i + 1, i + 2);
            String pairVals = char1 + char2;

            int[] pair1 = makePair(char1, table);
            int[] pair2 = makePair(char2, table);
            if (pair1[0] == pair2[0]) {
                pairVals = verticalEncode(pairVals, table);
            } else {
                if (pair1[1] == pair2[1]) {
                    pairVals = horizontalEncode(pairVals, table);
                } else {
                    pairVals = regular(pairVals, table);
                }
            }
            output = output + pairVals;
            i = i + 2;
        }
        return output;
    }
    
    public static String decode(String input, String[][] table) {
        String output = "";
        
        int len = input.length() - 1;
        int i = 0;
        while (i < len) {
            String char1 = "" + input.substring(i, i + 1);
            String char2 = "";
            if (i + 1 < len) {
                char2 = char2 + input.substring(i + 1, i + 2);
            } else {
                char2 = char2 + input.substring(i + 1);
            }
            String pairVals = char1 + char2;

            int[] pair1 = makePair(char1, table);
            int[] pair2 = makePair(char2, table);
            if (pair1[0] == pair2[0]) {
                pairVals = verticalDecode(pairVals, table);
            } else {
                if (pair1[1] == pair2[1]) {
                    pairVals = horizontalDecode(pairVals, table);
                } else {
                    pairVals = regular(pairVals, table);
                }
            }
            output = output + pairVals;
            i = i + 2;
        }
        return output;
    }

    public static void main(String[] args) {
        String chars = args[1].toUpperCase();  // text
        String keyStr = args[2].toUpperCase(); // key
        String[][] keytable = makeTable(keyStr);

        if (args[0].equals("encode")) { // args[0] is the command
            System.out.print(encode(chars, keyTable));
        } else { // "decode"
            System.out.print(decode(chars, keyTable));
        }
        System.out.println();
    }
}
