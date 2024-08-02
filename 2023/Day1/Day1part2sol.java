/*

--- Part Two ---
Your calculation isn't quite right.
It looks like some of the digits are actually spelled out with
letters: one, two, three, four, five, six, seven, eight, and nine
also count as valid "digits".

Equipped with this new information,
you now need to find the real first and last digit on each line. For example:

two1nine
eightwothree
abcone2threexyz
xtwone3four
4nineeightseven2
zoneight234
7pqrstsixteen
In this example, the calibration values are 29, 83, 13, 24, 42, 14, and 76.
Adding these together produces 281.

What is the sum of all of the calibration values?

 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Day1part2sol {

    // create a dictionary (hash map) for storing numbers letters
    private static final HashMap<String, String> NUMS_DICT = new HashMap<>() {
        {
            put("one", "1");
            put("two", "2");
            put("three", "3");
            put("four", "4");
            put("five", "5");
            put("six", "6");
            put("seven", "7");
            put("eight", "8");
            put("nine", "9");
        }
    };

    public static void main(String[] args) throws IOException {
        // reading input from the Calibration.txt file
        BufferedReader reader = new BufferedReader(
            new FileReader("CalibrationDocument.txt")
        );

        int sum = 0;
        String line;
        while ((line = reader.readLine()) != null) {
            line = parseLine(line);
            sum += calc(line);
        }

        System.out.println(sum);
    }

    // this function loops on the line from both sides,
    // in each iteration, it adds a char from beginning to 'forward'
    // and a char from the ending to 'backward'
    // then concatenate forward and backward in 'temp'
    // and check for any matching in the nums dictionary
    // if found, replace the letter with the number in temp,
    // and also in forward and backward.
    // the loop goes line.length() times, which results
    // in doubling the line string, looks like this solves some
    // hidden problem :)
    private static String parseLine(String line) {
        String forward = "";
        String backward = "";
        String temp = "";

        for (int i = 0, j = line.length() - 1; i < line.length(); i++, j--) {
            forward += line.charAt(i);
            backward = line.charAt(j) + backward;
            temp = forward + backward;

            for (Map.Entry<String, String> entry : NUMS_DICT.entrySet()) {
                temp = temp.replace(entry.getKey(), entry.getValue());
                forward = forward.replace(entry.getKey(), entry.getValue());
                backward = backward.replace(entry.getKey(), entry.getValue());
            }
        }

        return temp;
    }

    // same func from day1part1
    private static int calc(String line) {
        int sum = 0;
        char num1 = ' ';
        char medDigit = ' ';
        char num2 = ' ';
        String value = "";

        boolean isFirst = true;
        medDigit = ' ';
        for (int i = 0; i < line.length(); i++) {
            if (Character.isDigit(line.charAt(i))) {
                // first digit check block
                if (isFirst) {
                    num1 = line.charAt(i);
                    isFirst = false;
                    continue;
                }

                // store the digits in medDigit until the end of the loop
                medDigit = line.charAt(i);
            }
        }

        // check if the line consisits of only one digit
        if (medDigit == ' ') medDigit = num1;

        // num2 = the last found digit (last med digit)
        num2 = medDigit;
        value = num1 + "" + num2;

        // parse the value and add it to sum
        int add = Integer.parseInt(value);

        return sum += add;
    }
}
// Answer = 57345
