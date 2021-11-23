package sorting;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static void parseArguments(List<String> argsList) {
        boolean isError = false;
        boolean isCountSortingType = false;
        boolean skip = false;

        DataParser parser = new LineParser();

        checkingLoop:
        for (int i = 0; i < argsList.size(); i++) {
            if (skip) {
                skip = false;
                continue;
            }

            if (argsList.get(i).equals("-dataType")) {
                if (argsList.size() <= i + 1) {
                    System.out.println("No data type defined!");
                    isError = true;
                    break checkingLoop;
                }

                switch(argsList.get(i + 1)) {
                    case "long":
                        parser = new NumberParser();
                        break;
                    case "line":
                        parser = new LineParser();
                        break;
                    case "word":
                        parser = new WordParser();
                        break;
                    default:
                        System.out.println("No data type defined!");
                        isError = true;
                        break checkingLoop;
                }

                skip = true;
            } else if (argsList.get(i).equals("-sortingType")) {
                if (argsList.size() <= i + 1) {
                    System.out.println("No sorting type defined!");
                    isError = true;
                    break;
                }

                if (argsList.get(i + 1).equals("byCount")) {
                    isCountSortingType = true;
                }

                skip = true;
            } else if (argsList.get(i).equals("-inputFile") ||
                       argsList.get(i).equals("-outputFile")) {
                skip = true;
            } else {
                System.out.printf("\"%s\" is a not a valid parameter. It will be skipped.\n", argsList.get(i));
            }
        }

        if (isError) {
            return;
        }


        if (argsList.contains("-inputFile")) {
            Scanner scanner = null;
            try {
                scanner = new Scanner(new File(argsList.get(argsList.indexOf("-inputFile") + 1)));
                String text = scanner.useDelimiter("\\A").next();
                parser.setInput(text);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                assert scanner != null;
                scanner.close();
            }
        } else {
            parser.readInput();
        }


        final String output;
        if (isCountSortingType) {
            output = parser.sortOnCount();
        } else {
            output = parser.sort();
        }

        if (argsList.contains("-outputFile")) {
            try {
                PrintWriter writer = new PrintWriter(argsList.get(argsList.indexOf("-outputFile") + 1));
                writer.println(output);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println(output);
        }
    }
    public static void main(String[] args) {
        final List<String> argsList = Arrays.asList(args);
        parseArguments(argsList);
    }
}
