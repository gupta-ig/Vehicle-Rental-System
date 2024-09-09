package com.wg.helper;

import com.wg.app.App;

public class Choice {
	public static int enterChoice() {
		int choice;
		while (true) {
		    if (App.scanner.hasNextInt()) {
		        choice = App.scanner.nextInt();
		        break;
		    } else {
		        System.out.println("Invalid choice. Please enter a valid integer.");
		        App.scanner.next();
		        System.out.print(StringConstants.ENTER_YOUR_CHOICE);
		    }
		}
		return choice;
	}
}
