package archivos;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.NumberFormatException;

public class Delete {
    
    public static void main(String data[]){
        try{
            String newName = data[0];
            String nameNumberString;
            String name;
            long number;
            int index;
 
            File file = new File("friendsContact.txt");
 
            if (!file.exists()) {
 
                file.createNewFile();
            }
 
            RandomAccessFile raf
                = new RandomAccessFile(file, "rw");
            boolean found = false;
 
            while (raf.getFilePointer() < raf.length()) {
 
                nameNumberString = raf.readLine();
 
                String[] lineSplit
                    = nameNumberString.split("!");
 
                name = lineSplit[0];
                number = Long.parseLong(lineSplit[1]);
 
                if (name == newName) {
                    found = true;
                    break;
                }
            }
 
            if (found == true) {
 
                File tmpFile = new File("temp.txt");

                RandomAccessFile tmpraf
                    = new RandomAccessFile(tmpFile, "rw");
 
                raf.seek(0);
 
                while (raf.getFilePointer()
                       < raf.length()) {
 
                    nameNumberString = raf.readLine();
 
                    index = nameNumberString.indexOf('!');
                    name = nameNumberString.substring(
                        0, index);
 

                    if (name.equals(newName)) {
 
                        continue;
                    }

                    tmpraf.writeBytes(nameNumberString);
 
                    tmpraf.writeBytes(
                        System.lineSeparator());
                }
                raf.seek(0);
                tmpraf.seek(0);

                while (tmpraf.getFilePointer()
                       < tmpraf.length()) {
                    raf.writeBytes(tmpraf.readLine());
                    raf.writeBytes(System.lineSeparator());
                }
 

                raf.setLength(tmpraf.length());
 
                tmpraf.close();
                raf.close();
 
                tmpFile.delete();
 
                System.out.println(" Friend deleted. ");
            }
            else {
                raf.close();
 
                System.out.println(" New name"
                                   + " does not exists. ");
            }
        }
        catch (IOException ioe) {
            System.out.println(ioe);
        }
        
        }
    }
