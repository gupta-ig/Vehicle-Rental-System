package com.wg.helper;

public class Printer {
	private static final String BOX_CHAR = "-";
    private static final int BOX_WIDTH = 80; // Width of the box
    static final String BOX_BORDER = "=====================================================================================================================================";
    static final String DIVIDER_LINE = "-------------------------------------------------------------------------------------------------------------------------------------";
    
    
    public void printHeader(String header) {
        printLine();
        printCenteredLine(header);
        printLine();
    }
 
    private void printLine() {
        System.out.println(BOX_CHAR.repeat(BOX_WIDTH));
    }
 
    private void printCenteredLine(String text) {
        int padding = (BOX_WIDTH - text.length()) / 2;
        String line = BOX_CHAR + " " + " ".repeat(padding) + text + " ".repeat(padding) + " " + BOX_CHAR;
        System.out.println(line);
    }
    
    // Helper method to center the text within a box
    public static String centerTextInBox(String text) {
        int boxWidth = BOX_BORDER.length();
        int textLength = text.length();
        int padding = (boxWidth - textLength) / 2;
 
        // Creating a line with centered text surrounded by spaces
        StringBuilder centeredText = new StringBuilder();
        centeredText.append(" ".repeat(padding));
        centeredText.append(text);
        centeredText.append(" ".repeat(padding));
 
        // Ensure the line is exactly as wide as the box, accounting for odd width
        while (centeredText.length() < boxWidth) {
            centeredText.append(" ");
        }
 
        return centeredText.toString();
    }
}
