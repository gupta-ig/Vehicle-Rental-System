package com.wg.helper;

import com.wg.app.App;

public class Logout {
	public static void logout() {
		while(true) {
			System.out.println(StringConstants.LOGGING_OUT);
			
			System.out.print(StringConstants.DO_YOU_WANT_TO_CONTINUE);
			
		    String continueChoice = App.scanner.next();
		    if(continueChoice.equalsIgnoreCase("y")) {
		    	break;
		    }
		    else if(continueChoice.equalsIgnoreCase("n")) {
		    	System.out.println(StringConstants.THANK_YOU_FOR_VISITING);
		    	System.exit(0);
		    	break;
		    }
		    else {
		    	System.out.println(StringConstants.INVALID_INPUT);
		    }
		}
	}
}
