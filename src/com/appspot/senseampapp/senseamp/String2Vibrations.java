package com.appspot.senseampapp.senseamp;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class String2Vibrations {
	public Long dot = 200L;
	public Long dash = 3*dot;
	public Long lspa = 3*dot;
	public Long wspa = 7*dot;
	
	public long[] getVibrations(String input) {
		ArrayList<Long> vibrations =  new ArrayList<Long>();
		int i;
		char character;
		
		for (i=0;i<input.length();i++) {
			System.out.println(input.charAt(i));
			
			character = Character.toUpperCase(input.charAt(i));
			
			if (character == ' ') 
				vibrations.addAll(Arrays.asList(wspa, 0L));
			else if (character == 'A') 
				vibrations.addAll(Arrays.asList(lspa,dot,lspa,dash));
			else if (character == 'B') 
				vibrations.addAll(Arrays.asList(lspa,dash,lspa,dot,lspa,dot,lspa,dot));
			else if (character == 'C') 
				vibrations.addAll(Arrays.asList(lspa,dash,lspa,dot,lspa,dash,lspa,dot));
			else if (character == 'D') 
				vibrations.addAll(Arrays.asList(lspa,dash,lspa,dot,lspa,dot));
			else if (character == 'E') 
				vibrations.addAll(Arrays.asList(lspa,dot));
			else if (character == 'F') 
				vibrations.addAll(Arrays.asList(lspa,dot,lspa,dot,lspa,dash,lspa,dot));
			else if (character == 'G') 
				vibrations.addAll(Arrays.asList(lspa,dash,lspa,dash,lspa,dot));
			else if (character == 'H') 
				vibrations.addAll(Arrays.asList(lspa,dot,lspa,dot,lspa,dot,lspa,dot));
			else if (character == 'I') 
				vibrations.addAll(Arrays.asList(lspa,dot,lspa,dot));
			else if (character == 'J') 
				vibrations.addAll(Arrays.asList(lspa,dot,lspa,dash,lspa,dash,lspa,dash));
			else if (character == 'K') 
				vibrations.addAll(Arrays.asList(lspa,dash,lspa,dot,lspa,dash));
			else if (character == 'L') 
				vibrations.addAll(Arrays.asList(lspa,dot,lspa,dash,lspa,dot,lspa,dot));
			else if (character == 'M') 
				vibrations.addAll(Arrays.asList(lspa,dash,lspa,dash));
			else if (character == 'N') 
				vibrations.addAll(Arrays.asList(lspa,dash,lspa,dot));
			else if (character == 'O') 
				vibrations.addAll(Arrays.asList(lspa,dash,lspa,dash,lspa,dash));
			else if (character == 'P') 
				vibrations.addAll(Arrays.asList(lspa,dot,lspa,dash,lspa,dash,lspa,dot));
			else if (character == 'Q') 
				vibrations.addAll(Arrays.asList(lspa,dash,lspa,dash,lspa,dot,lspa,dash));
			else if (character == 'R') 
				vibrations.addAll(Arrays.asList(lspa,dot,lspa,dash,lspa,dot));
			else if (character == 'S') 
				vibrations.addAll(Arrays.asList(lspa,dot,lspa,dot,lspa,dot));
			else if (character == 'T') 
				vibrations.addAll(Arrays.asList(lspa,dash));
			else if (character == 'U') 
				vibrations.addAll(Arrays.asList(lspa,dot,lspa,dot,lspa,dash));
			else if (character == 'V') 
				vibrations.addAll(Arrays.asList(lspa,dot,lspa,dot,lspa,dot,lspa,dash));
			else if (character == 'W') 
				vibrations.addAll(Arrays.asList(lspa,dot,lspa,dash,lspa,dash));				
			else if (character == 'X') 
				vibrations.addAll(Arrays.asList(lspa,dash,lspa,dot,lspa,dot,lspa,dash));					
			else if (character == 'Y') 
				vibrations.addAll(Arrays.asList(lspa,dash,lspa,dot,lspa,dash,lspa,dash));			
			else if (character == 'Z') 
				vibrations.addAll(Arrays.asList(lspa,dash,lspa,dash,lspa,dot,lspa,dot));		
			else if (character == '1') 
				vibrations.addAll(Arrays.asList(lspa,dot,lspa,dash,lspa,dash,lspa,dash,lspa,dash));
			else if (character == '2') 
				vibrations.addAll(Arrays.asList(lspa,dot,lspa,dot,lspa,dash,lspa,dash,lspa,dash));
			else if (character == '3') 
				vibrations.addAll(Arrays.asList(lspa,dot,lspa,dot,lspa,dot,lspa,dash,lspa,dash));
			else if (character == '4') 
				vibrations.addAll(Arrays.asList(lspa,dot,lspa,dot,lspa,dot,lspa,dot,lspa,dash));
			else if (character == '5') 
				vibrations.addAll(Arrays.asList(lspa,dot,lspa,dot,lspa,dot,lspa,dot,lspa,dot));
			else if (character == '6') 
				vibrations.addAll(Arrays.asList(lspa,dash,lspa,dot,lspa,dot,lspa,dot,lspa,dot));
			else if (character == '7') 
				vibrations.addAll(Arrays.asList(lspa,dash,lspa,dash,lspa,dot,lspa,dot,lspa,dot));	
			else if (character == '8') 
				vibrations.addAll(Arrays.asList(lspa,dash,lspa,dash,lspa,dash,lspa,dot,lspa,dot));	
			else if (character == '9') 
				vibrations.addAll(Arrays.asList(lspa,dash,lspa,dash,lspa,dash,lspa,dash,lspa,dot));	
			else if (character == '0') 
				vibrations.addAll(Arrays.asList(lspa,dash,lspa,dash,lspa,dash,lspa,dash,lspa,dash));				
			
		}
		
		long[] result = new long[vibrations.size()];
		for(i=0;i<vibrations.size();i++) result[i] = vibrations.get(i);		
		
		return result;		
	}
}
