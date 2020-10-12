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

    // For Double Letters
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
        for (int i = 0; i < len; i++) {
            if ((txt.substring(i, i + 1)) == "J") {
                newTxt = newTxt + "I";
            } else {
                newTxt = newTxt + txt.substring(i, i + 1);
            }
        }
        return newTxt;
    }

    public static String encode(String text, String[][] table) {
        return text;
    }

    public static String decode(String text, String[][] table) {
        return text;
    }

    public static void main(String[] args) {
        String chars = args[1].toUpperCase();  // text
        String keyStr = args[2].toUpperCase(); // key
        String[][] keytable = makeTable(keyStr);

        if (args[0].equals("encode")) { // arg[0] is either encode or decode
            System.out.println(encode(chars, keytable));
        } else {
            System.out.println(decode(chars, keytable));
        }
    }
}
