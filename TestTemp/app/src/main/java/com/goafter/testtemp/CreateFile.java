package com.goafter.testtemp;

import java.io.File;
import java.io.IOException;

/**
 * Created by huashuolee on 2015/8/13.
 */
public class CreateFile {
    public void main (String[] args){
        File file = new File("a.txt");
        if (file.exists()!=false) {
            try {
                file.hashCode();
                file.createNewFile();
                file.delete();
                System.out.println("create file");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
