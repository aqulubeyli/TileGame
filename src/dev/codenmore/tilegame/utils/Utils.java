package dev.codenmore.tilegame.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by aydin on 4/30/17.
 */
public class Utils {

    public static String loadFileAsString(String path){
        StringBuilder builder = new StringBuilder();

        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;

            while((line = br.readLine())!=null)
                builder.append(line+"\n");
                br.close();
        }catch(IOException ex){
            ex.printStackTrace();
        }

        return builder.toString();
    }

    public static int parseInt(String number){
        try{
            return Integer.parseInt(number);
        }catch(NumberFormatException ne){
            ne.printStackTrace();
        }
        return 0;
    }
}
