package Model;

import java.io.*;
import java.util.List;

public class ObjectFileStore {

    private static final ObjectFileStore objectFileStore = new ObjectFileStore();

    private ObjectFileStore(){
    }

    public static ObjectFileStore getObjectFileStore() {
        return objectFileStore;
    }

    public void storeObjectList(List<?> objectList, String fileName){
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(
                "resources/files/" + fileName.trim() + ".ser", false))) {
            out.writeObject(objectList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<?> retrieveObjectList(String fileName){
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(
                "resources/files/" + fileName.trim() + ".ser"))) {
            return (List<?>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}