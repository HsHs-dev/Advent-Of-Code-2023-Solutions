/*
---- Day 1: Trebuchet?! ---

Something is wrong with global snow production, and you've been selected to take a look.
The Elves have even given you a map; on it, they've used stars to mark the top fifty locations 
that are likely to be having problems.

You've been doing this long enough to know that to restore snow operations,
you need to check all fifty stars by December 25th.

Collect stars by solving puzzles. 
Two puzzles will be made available on each day in the Advent calendar; 
the second puzzle is unlocked when you complete the first. Each puzzle grants one star. Good luck!

You try to ask why they can't just use a weather machine ("not powerful enough") 
and where they're even sending you ("the sky") and why your map looks mostly blank 
("you sure ask a lot of questions") and hang on did you just say the sky 
("of course, where do you think snow comes from") 
when you realize that the Elves are already loading you into a trebuchet
("please hold still, we need to strap you in").

As they're making the final adjustments, they discover that their calibration document (your puzzle input) has been amended by a very young Elf
who was apparently just excited to show off her art skills.
Consequently, the Elves are having trouble reading the values on the document.

The newly-improved calibration document consists of lines of text; 
each line originally contained a specific calibration value that the Elves now need to recover.
On each line, the calibration value can be found by combining the first digit and the last digit
(in that order) to form a single two-digit number.

For example:

1abc2
pqr3stu8vwx
a1b2c3d4e5f
treb7uchet

In this example, the calibration values of these four lines are
12, 38, 15, and 77. Adding these together produces 142.

Consider your entire calibration document. What is the sum of all of the calibration values?


 */

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;


public class Day1part1sol {

    // logic: reading the CalibrationDocument.txt line by line
    // in each line, looking for first digit and store it, and going through
    // each digit and put it in a medDigit var, until the end of the line,
    // combine the first digit and the last med digit (which is the last digit)
    // parse the value, and add it to sum var.

    public static void main(String[] args) throws IOException {

        // reading input from the Calibration.txt file
        BufferedReader reader = new BufferedReader(new FileReader("CalibrationDocument.txt"));
        
        // looping through the file

        int sum = 0;
        char num1 = ' ';
        char medDigit = ' ';
        char num2 = ' ';
        String value = "";
        String line;

        while ((line = reader.readLine()) != null) {

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
            sum += add;


        } // end of while loop

        // close the reader
        reader.close();

        System.out.println(sum);

    } // end of main method

} // end of the class

// Answer = 57346
// NOTE: The answer depends on the input document, which differs from account to other.