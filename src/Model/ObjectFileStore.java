package Model;

import java.io.*;
import java.util.List;

public class ObjectFileStore {

    public static void storeObjectList(List<?> objectList, String fileName){
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(
                "resources/files/" + fileName.trim() + ".ser", true))) {
            out.writeObject(objectList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<?> retrieveObjectList(String fileName){
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(
                "resources/files/" + fileName.trim() + ".ser"))) {
            return (List<?>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}