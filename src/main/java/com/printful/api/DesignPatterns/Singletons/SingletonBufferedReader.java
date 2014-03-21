package com.printful.api.DesignPatterns.Singletons;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * For the uninitiated:
 * http://en.wikipedia.org/wiki/Singleton_pattern
 */
public class SingletonBufferedReader {

    private static BufferedReader br = null;

    public static BufferedReader getBufferedReader(String nameOfFile)
    {
        if(br == null)
        {
            try
            {
                br = new BufferedReader(new FileReader(nameOfFile));
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        return br;
    }


    public static BufferedReader getBufferedReader()
    {
        return br;
    }
}
