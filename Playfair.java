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
        for (int i = 0; i < len - 1; i += 2) {
            if ((txt.substring(i, i + 1)) == (txt.substring(i + 1, i + 2))) {
                txt = txt.substring(0, i + 1) + "X" + txt.substring(i + 1);
            }
        }
        return txt;
    }

    // Remove J and Replace with I instead
    public static String JtoI(String txt) {
        String newTxt = "";
        int len = txt.length();
        int i = 0;
        while (i < len) {
            if ((txt.substring(i, i + 1)) == "J") {
                newTxt = newTxt + "I";
            } else {
                newTxt = newTxt + txt.substring(i, i + 1);
            }
            i++;
        }
        return newTxt;
    }

    // If odd string length, add "Z"
    public static String addZ(String txt) {
        if (txt.length() % 2 != 0) {
            txt = txt + "Z";
        }
        return txt;
    }
    
    public static String[] encodePairs(String input) {
        String text = JtoI(input);
        text = doubles(text);
        int len = text.length();
        int half = len / 2;
        String[] rowColPairs = new String[half];
        int index = 0;
        for (int val = 0; val < half; val++) {
            rowColPairs[val] = text.substring(index, index + 2);
            index = index + 2;
        }
        return rowColPairs;
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
            String[] pairsEncode = encodePairs(chars);
            int len = pairsEncode.length;
            for (int val = 0; val < len; val++) {
                System.out.print(encode(pairsEncode[val], keytable));
            }
        } else {
            String[] pairsDecode = decodePairs(chars);
            int len = pairsDecode.length;
            for (int val = 0; val < len; val++) {
                while (!(pairsDecode[val] == null)) {
                    System.out.print(decode(pairsDecode[val], keytable));
                }
            }
        }
        System.out.println();
    }
}
