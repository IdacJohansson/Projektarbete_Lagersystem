package Model;

import java.io.*;
import java.util.List;

public class ObjectFileStore {

    public static void storeObjectList(List<?> objectList, String fileName){
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(
                "resources/files/" + fileName.trim() + ".ser", false))) {
            out.writeObject(objectList);
            System.out.println("storelist tried");
        } catch (IOException e) {
            System.out.println("storelist caught");
            //e.printStackTrace();
        }
    }

    public static List<?> retrieveObjectList(String fileName){
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(
                "resources/files/" + fileName.trim() + ".ser"))) {
            System.out.println("retrievelist tried");
            return (List<?>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("retrievelist caught");
            //e.printStackTrace();
        }
        return null;
    }
}