package com.appspot.senseampapp.senseamp;

import java.util.ArrayList;
import java.util.Arrays;

public class String2Vibrations {
	// Refer to http://morsecode.scphillips.com/morse2.html
	
	static private Long time = 200L;
    static private Long dit = time; // Short vibration
    static private Long dah = 3*time; // Long vibration
    static private Long cspa = 1*time; // Component space
    static private Long lspa = 3*time; // Letter space
    static private Long wspa = 4*time; // Word space (plus three from letter space)
    static private Long pspa = 6*time; // Paragraph space (not used)

    // Get time value
    static public void setTime(Long ntime) {
    	time = ntime;
    	dit = time;
    	dah = 3*time;
    	cspa = 1*time;
    	lspa = 3*time;
    	wspa = 4*time;
    	pspa = 6*time;
    }
    
    // Set time value
    static public Long getTime() {
    	return time;
    }
    
	// Get vibrations
    static public long[] getVibrations(String input) {
        //String spacedStr = spacedString(input);

        ArrayList<Long> pattern =  new ArrayList<Long>();
        int i;
        char character;

        for (i = 0; i < input.length(); i++) {
            //System.out.println(spacedStr.charAt(i)); //to print on to screen.

            character = Character.toUpperCase(input.charAt(i));

            if (character == ' ')
            	pattern.addAll(Arrays.asList(wspa, 0L));
            else if (character == '\n') // Denoted by AA
            	pattern.addAll(Arrays.asList(lspa, dit, cspa, dah, lspa, dit, cspa, dah));
            else if (character == 'A')
            	pattern.addAll(Arrays.asList(lspa, dit, cspa, dah));
            else if (character == 'B')
            	pattern.addAll(Arrays.asList(lspa,dah,cspa,dit,cspa,dit,cspa,dit));
            else if (character == 'C')
            	pattern.addAll(Arrays.asList(lspa,dah,cspa,dit,cspa,dah,cspa,dit));
            else if (character == 'D')
            	pattern.addAll(Arrays.asList(lspa,dah,cspa,dit,cspa,dit));
            else if (character == 'E')
            	pattern.addAll(Arrays.asList(lspa,dit));
            else if (character == 'F')
            	pattern.addAll(Arrays.asList(lspa,dit,cspa,dit,cspa,dah,cspa,dit));
            else if (character == 'G')
            	pattern.addAll(Arrays.asList(lspa,dah,cspa,dah,cspa,dit));
            else if (character == 'H')
            	pattern.addAll(Arrays.asList(lspa,dit,cspa,dit,cspa,dit,cspa,dit));
            else if (character == 'I')
            	pattern.addAll(Arrays.asList(lspa,dit,cspa,dit));
            else if (character == 'J')
            	pattern.addAll(Arrays.asList(lspa,dit,cspa,dah,cspa,dah,cspa,dah));
            else if (character == 'K')
            	pattern.addAll(Arrays.asList(lspa,dah,cspa,dit,cspa,dah));
            else if (character == 'L')
            	pattern.addAll(Arrays.asList(lspa,dit,cspa,dah,cspa,dit,cspa,dit));
            else if (character == 'M')
            	pattern.addAll(Arrays.asList(lspa,dah,cspa,dah));
            else if (character == 'N')
            	pattern.addAll(Arrays.asList(lspa,dah,cspa,dit));
            else if (character == 'Ñ')
            	pattern.addAll(Arrays.asList(lspa,dah,cspa,dah,cspa,dit,cspa,dah,cspa,dah));
            else if (character == 'O')
            	pattern.addAll(Arrays.asList(lspa,dah,cspa,dah,cspa,dah));
            else if (character == 'P')
            	pattern.addAll(Arrays.asList(lspa,dit,cspa,dah,cspa,dah,cspa,dit));
            else if (character == 'Q')
            	pattern.addAll(Arrays.asList(lspa,dah,cspa,dah,cspa,dit,cspa,dah));
            else if (character == 'R')
            	pattern.addAll(Arrays.asList(lspa,dit,cspa,dah,cspa,dit));
            else if (character == 'S')
            	pattern.addAll(Arrays.asList(lspa,dit,cspa,dit,cspa,dit));
            else if (character == 'T')
            	pattern.addAll(Arrays.asList(lspa,dah));
            else if (character == 'U')
            	pattern.addAll(Arrays.asList(lspa,dit,cspa,dit,cspa,dah));
            else if (character == 'V')
            	pattern.addAll(Arrays.asList(lspa,dit,cspa,dit,cspa,dit,cspa,dah));
            else if (character == 'W')
            	pattern.addAll(Arrays.asList(lspa,dit,cspa,dah,cspa,dah));
            else if (character == 'X')
            	pattern.addAll(Arrays.asList(lspa,dah,cspa,dit,cspa,dit,cspa,dah));
            else if (character == 'Y')
            	pattern.addAll(Arrays.asList(lspa,dah,cspa,dit,cspa,dah,cspa,dah));
            else if (character == 'Z')
            	pattern.addAll(Arrays.asList(lspa,dah,cspa,dah,cspa,dit,cspa,dit));
            else if (character == '1')
            	pattern.addAll(Arrays.asList(lspa,dit,cspa,dah,cspa,dah,cspa,dah,cspa,dah));
            else if (character == '2')
            	pattern.addAll(Arrays.asList(lspa,dit,cspa,dit,cspa,dah,cspa,dah,cspa,dah));
            else if (character == '3')
            	pattern.addAll(Arrays.asList(lspa,dit,cspa,dit,cspa,dit,cspa,dah,cspa,dah));
            else if (character == '4')
            	pattern.addAll(Arrays.asList(lspa,dit,cspa,dit,cspa,dit,cspa,dit,cspa,dah));
            else if (character == '5')
            	pattern.addAll(Arrays.asList(lspa,dit,cspa,dit,cspa,dit,cspa,dit,cspa,dit));
            else if (character == '6')
            	pattern.addAll(Arrays.asList(lspa,dah,cspa,dit,cspa,dit,cspa,dit,cspa,dit));
            else if (character == '7')
            	pattern.addAll(Arrays.asList(lspa,dah,cspa,dah,cspa,dit,cspa,dit,cspa,dit));
            else if (character == '8')
            	pattern.addAll(Arrays.asList(lspa,dah,cspa,dah,cspa,dah,cspa,dit,cspa,dit));
            else if (character == '9')
            	pattern.addAll(Arrays.asList(lspa,dah,cspa,dah,cspa,dah,cspa,dah,cspa,dit));
            else if (character == '0')
            	pattern.addAll(Arrays.asList(lspa,dah,cspa,dah,cspa,dah,cspa,dah,cspa,dah));
            else
            	pattern.addAll(Arrays.asList(dit,dit));
        }

        long[] result = new long[pattern.size()];
        for(i = 0; i<pattern.size(); i++){
            result[i] = pattern.get(i);
        }

        // Return vibration pattern
        return result;
    }

    // ----------------------------------------------------------
     /**
     * parse the string and add spaces in between letters
     * and have double space in a space in between words.
     * @param word
     * @return new word with spaces
     */

/*    
    public String spacedString(String word) {

            char[] chars = word.toCharArray();
            String newString = "";
            for(int i = 0; i < chars.length; i++) {
                if (i == (chars.length-1)) {
                    newString += chars[i];
                }
                else{
                    if (chars[i] != ' ') {
                    newString+=chars[i];
                    newString += " ";
                    }
                    else {
                        newString+=chars[i];
                    }
                }
            }
        return newString;
    }
*/

}


