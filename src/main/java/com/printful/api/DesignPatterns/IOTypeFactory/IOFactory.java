package com.printful.api.DesignPatterns.IOTypeFactory;

/*
* For the uninitiated:
* http://en.wikipedia.org/wiki/Factory_method_pattern
* */
public class IOFactory
{
    public static IOBufferedWriter buildIOBufferedWriter(String filePath)
    {
        return new IOBufferedWriter(filePath);
    }

    public static IOBufferedReader buildIOBufferedReader(String filePath)
    {
        return new IOBufferedReader(filePath);
    }
}
