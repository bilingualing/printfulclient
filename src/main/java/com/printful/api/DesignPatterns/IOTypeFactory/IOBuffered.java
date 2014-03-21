package com.printful.api.DesignPatterns.IOTypeFactory;

/*
* For the uninitiated:
* http://en.wikipedia.org/wiki/Factory_method_pattern
* */
public abstract class IOBuffered
{
    public IOBuffered()
    {
        super();
    }

    public IOBuffered(IOType type)
    {
        this.ioType = type;
    }

    private IOType ioType = null;

    //Do subclass level processing in this method
    protected abstract void construct(String filePath);


    public IOType getIoType()
    {
        return ioType;
    }

    public void setIoType(IOType type)
    {
        this.ioType = type;
    }
}
