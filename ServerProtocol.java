package knockknockserver;

import java.net.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServerProtocol
{
    private static final int STUDENT_NUMBER = 0;
    private static final int STUDENT_NAME = 1;
    private static final int STUDENT_FACULTY = 2;
    private static final int PERSONAL_CODE = 3;

    private static final int END_STATE = 5;

    private int state = STUDENT_NUMBER;
//    private int currentJoke = 0;

    private String[] clues = { "Turnip", "Little Old Lady", "Atch", "Who", "Who" };
    private String[] answers = { "Turnip the heat, it's cold in here!",
                                 "I didn't know you could yodel!",
                                 "Bless you!",
                                 "Is there an owl in here?",
                                 "Is there an echo in here?" };

    public static boolean isValid(String id)
    {
     Pattern p = Pattern.compile ("^\\d{6}$");
     Matcher m = p.matcher(id);
     return(m.matches());
    }
    public String processInput(String theInput)
    {
        String theOutput = null;

        if (state == STUDENT_NUMBER)
        {
            theOutput = "Please send your Student Number";
            state = STUDENT_NAME;
        }
        else if (state == STUDENT_NAME)
        {
            if (isValid(theInput))
            {
                theOutput = "Please input your First Name and Last Name";
                state = STUDENT_FACULTY;
            }
            else
            {
                theOutput = "You're supposed to input your Student Number. It should be a 6 digit number.";
                state = STUDENT_NUMBER;
            }
        }
        else if (state == STUDENT_FACULTY)
        {
            if (theInput instanceof String)
            {
                theOutput = "Please input your Faculty and Course";
                state = PERSONAL_CODE;
            }
            else
            {
                theOutput = "You are supposed to input your first name and last name";
                state = STUDENT_NAME;
            }
        }
        else if (state == PERSONAL_CODE)
        {
            if (theInput instanceof String)
            {
                theOutput = "Please input a random stream of characters. This will used as your personal code.";
                state = END_STATE;
            }
            else
            {
                theOutput = "Please input your Faculty, Course and Degree";
                state = STUDENT_FACULTY;
            }
        }
        else
        {
            theOutput = "Thank you for your input. That is all I need. Bye!!!";
        }
        return theOutput;
    }
}
