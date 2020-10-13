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
        int[] pair1 = makePair(pair.substring(0, 1), table);
        int[] pair2 = makePair(pair.substring(1), table);
        int row = pair1[0] + 1;
        if (row < 0) {
            row = 4;
        }
        if (row > 4) {
            row = 0;
        }
        String val1 = table[row][pair1[1]];
        String val2 = table[row][pair2[1]];
        String newPair = val1 + val2;
        return newPair;
    }

    public static String horizontalEncode(String pair, String[][] table) {
        int[] pair1 = makePair(pair.substring(0, 1), table);
        int[] pair2 = makePair(pair.substring(1), table);
        int column = pair1[1] + 1;
        if (column < 0) {
            column = 4;
        }
        if (column > 4) {
            column = 0;
        }
        String val1 = table[pair1[0]][column];
        String val2 = table[pair2[0]][column];
        String newPair = val1 + val2;
        return newPair;
    }
    
    public static String encode(String input, String[][] table) {
        String text = JtoI(input);
        text = doubles(text);
        text = addZ(text);
        String output = "";
        return output;
    }
    
    public static String decode(String input, String[][] table) {
        String output = "";
        return output;
    }

    public static void main(String[] args) {
        String chars = args[1].toUpperCase();  // text
        String keyStr = args[2].toUpperCase(); // key
        String[][] keytable = makeTable(keyStr);

        if (args[0].equals("encode")) {
            System.out.print(encode(chars, keyTable));
        } else {
            System.out.print(decode(chars, keyTable));
        }
        System.out.println();
    }
}
