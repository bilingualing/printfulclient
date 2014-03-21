package com.printful.api.DesignPatterns.IOTypeFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/*
* For the uninitiated:
* http://en.wikipedia.org/wiki/Factory_method_pattern
* */
public class IOBufferedReader extends IOBuffered
{
    private BufferedReader br = null;
    public IOBufferedReader(String filePath)
    {
        super(IOType.BUFFERED_READER);
        this.construct(filePath);
    }

    @Override
    protected void construct(String filePath)
    {
        try
        {
            this.br = new BufferedReader(new FileReader(filePath));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    public BufferedReader getBufferedReader()
    {
        return this.br;
    }
}
