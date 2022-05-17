/*
 * classAverage.java Version#1.0
 * Frank Bernal
 * CIS 084 Java Programming
 * Input: firstName, lastName, grades[]
 * Output: average, displayFile()
 * 17 May 2022
 */

 import java.util.Scanner;
 import java.util.InputMismatchException;
 import java.io.File;
 import java.io.FileWriter; 
 import java.io.BufferedWriter;
 import java.io.IOException;

 public class classAverage {
     
    public static final String FILE_PATH = "class.txt";
    // Start Main
     public static void main(String[] args) {
        // Declare Variables
        String firstName;       // Initialize String
        String lastName = " ";        // Initialize String
        int[] grades = new int[5];    // Array with 5 slots for grades
        boolean endProgram = false;   // Set flag for loops
        int userSelection = 0;    
        boolean badInput;             
        // Create scanner
        Scanner stdin = new Scanner(System.in);

        // Welcome user
        System.out.printf ("\n==============================");
        System.out.printf ("\n      Grade Inputter 1.0      ");
        System.out.printf ("\n==============================");

        // Start Menu loop
        while (!endProgram) {
            // Menu
            System.out.printf ("\n\nSelect from the following options");
            System.out.printf ("\n\n[1] - Enter Grades");
            System.out.printf ("\n[2] - Display All Grades");
            System.out.printf ("\n[3] - End program");

            // Start try block for userSelection
            try {
                // Get input
                System.out.printf ("\n\nEnter Selection: ");
                userSelection = stdin.nextInt();

                // Start conditional
                if (userSelection == 1) {
                    // Initialize Variables
                    int total = 0;          // Initialize total
                    double average = 0.0;   // Inititialize average
                    // Set flag
                    badInput = false;
                    // Ask for name
                    System.out.printf ("\nEnter student First and Last name: ");
                    firstName = stdin.next();
                    lastName = stdin.next();

                    // Ask for grades
                    System.out.printf ("\nEnter last 5 grades [0-100]: ");

                    // Read and store grades using loop
                    for (int i = 0; i < grades.length; i++) {
                        grades[i] = stdin.nextInt();
                        // Check for valid 
                        if (grades[i] < 0 || grades[i] > 100) {
                            System.out.printf ("\nGrades must be greater than 0 and less than 100");
                            stdin.nextLine();
                            badInput = true;
                            break;
                        }   // End of bad input
                        else
                            // Add grade total
                            total += grades[i];

                    }   // End of for loop

                    // Clear excess input
                    stdin.nextLine();

                    // Only compute avergae if good input
                    if (!badInput) {
                        // Compute average
                        average = total / grades.length;
                        
                        // Test print
                        System.out.printf ("\n%s %s", firstName, lastName);
                        for (int i = 0; i < grades.length; i++)
                            System.out.printf ("\nGrade %d: %d", i+1, grades[i]);
                        System.out.printf ("\nAverage: %.2f", average);
                    }   // End of output
                    writeFile(firstName, lastName, grades, average);
                }   // End of option 1

                else if (userSelection == 2) {
                    System.out.println ("Display Grades Selected");
                }   // End of option 2

                else if (userSelection == 3) {
                    System.out.printf ("\nThank you for using Grade Inputter 1.0\n\n");
                    endProgram = true;
                }   // End of option 3

                else 
                    System.out.println ("Invalid selection. Please try again\n");

            }   // End of try
            catch (InputMismatchException e) {
                // Alert User
                System.out.printf ("\nInvalid selection. Please try again\n");
                // Clear Buffer
                stdin.nextLine();
                // Reinitialize userSelection
                userSelection = 0;
            }   // End of catch

        }   // End of while !endProgram loop

        // Close Scanner
        stdin.close();

     }   // End of main

    // Start displayFile
    public static void displayFile() {

    }   // End of displayFile

    // Start writeFile
    public static void writeFile(String f, String l, int[] g, double a) {
        // Bring in variables
        String firstName = f;
        String lastName = l;
        int[] grades = g;
        double average = a; 
        String stringToWrite = " ";            // Blank String
        File dataFile = new File(FILE_PATH);   // Create file object

        // Start building string to write to file
        stringToWrite += firstName + " " + lastName + "\t";
        
        // Add grades to string
        for (int i = 0; i < grades.length; i++) {
            stringToWrite += grades[i] + "\t";
        }   // End of adding grades to string

        // Add average
        stringToWrite += average;

        // Try to open and write file
        try {
            // Create FileWriter object
            FileWriter classFile = new FileWriter(dataFile, true);
            BufferedWriter buffWriter = new BufferedWriter(classFile);
            // Write to file
            buffWriter.write(stringToWrite);
            buffWriter.write("\n");
            // Close File
            buffWriter.close();
        }   // End of Try
        catch (IOException e) {
            System.out.printf ("\nError writing file\n\n");
        }   // End of catch

    }   // End of writeFile

 }   // End of classAverage
 