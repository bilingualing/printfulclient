package com.printful.api.DesignPatterns.IOTypeFactory;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;


public class IOBufferedWriter extends IOBuffered
{
    private BufferedWriter bw = null;

    public IOBufferedWriter(String filePath)
    {
        super(IOType.BUFFERED_WRITTER);
        construct(filePath);
    }

    @Override
    protected void construct(String filePath)
    {
        try
        {
            this.bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath),"UTF-8"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public BufferedWriter getBufferedWriter()
    {
        return this.bw;
    }
}
