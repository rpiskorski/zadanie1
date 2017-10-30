package test;

import main.database;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;

public class databaseTest {
    @Test(expected = Exception.class)
    public void set_answerTEST() throws Exception {
        database.set_answer("1","FROM SELECT WHERE ORDER BY");

    }

    @Test
    public void checktimeTEST() throws Exception {
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        String string1="Uczelnia nie pracuje";
        String string2="";
        if(hour<8 || hour>18)
        {
            try {
                database.checktime();
            }catch(Exception e)
            {
                string2=e.getMessage();
            }
        assertEquals(string1,string2);
        }
        else
        {
            assertNotEquals(string1,string2);
        }


    }

}