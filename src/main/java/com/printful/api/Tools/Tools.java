package com.printful.api.Tools;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: jamaaltaylor
 * Date: 3/21/14
 * Time: 11:35 AM
 * To change this template use File | Settings | File Templates.
 */
public class Tools
{
    public static String makeFilePath(String addition)
    {
        String filePath = new File("").getAbsolutePath();
        filePath = filePath+addition;
        return filePath;
    }


}
