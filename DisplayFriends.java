
package archivos;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.NumberFormatException;

public class DisplayFriends {
    
    public static void main(String data[]){
        try{
            String nameNumberString;
            String name;
            long number;
            int index;
            
            File file=new File("friendsContact.txt");
            
            if(!file.exists()){
                file.createNewFile();
            }
            RandomAccessFile raf= new RandomAccessFile(file,"rw");
            boolean found=false;
            
            while(raf.getFilePointer() < raf.length()){
                nameNumberString=raf.readLine();
                String[] lineSplit= nameNumberString.split("!");
                name = lineSplit[0];
                number = Long.parseLong(lineSplit[1]);
                System.out.println(
                    "Friend Name: " + name + "\n"
                    + "Contact Number: " + number + "\n");
                
            }
        }
        catch(IOException ioe){
                System.out.println(ioe);
            }
        catch(NumberFormatException nef){
                System.out.println(nef);
        }
    }
}
