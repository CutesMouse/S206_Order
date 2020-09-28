package com.cutesmouse.s206Order.config;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

public class Config {
    public static File getConfigFile(String name) {
        return new File(System.getProperty("user.dir"),"config/"+name+".mdata");
    }
    private final File file;
    private HashMap<String,Object> data;
    public Config(File f) {
        file = f;
        loadData();
    }
    // dsdsa/sdasd
    public void loadData() {
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            data = new HashMap<>();
            return;
        }
        try {
            ObjectInputStream read = new ObjectInputStream(new FileInputStream(file));
            Object loaded = read.readObject();
            if (!(loaded instanceof HashMap)) return;
            this.data = ((HashMap<String, Object>) loaded);
            read.close();
        } catch (InvalidClassException e) {
            try {
                file.createNewFile();
                data = new HashMap<>();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, Object> getValues() {
        return data;
    }
    public String getString(String path) {
        return data.get(path).toString();
    }
    public int getInt(String path) {
        return Integer.parseInt(getString(path));
    }
    public double getDouble(String path) {
        return Double.parseDouble(getString(path));
    }
    public List<String> getStringList(String path) {
        Object o = data.get(path);
        if (!(o instanceof List)) return null;
        return (List<String>) o;
    }
    public <T> List<T> getList(String path, Class<T> init) {
        Object o = data.get(path);
        if (!(o instanceof List)) return null;
        return (List<T>) o;
    }
    public <T> T getObject(String path, Class<T> init) {
        Object o = data.get(path);
        if (o == null) return null;
        return (T) o;
    }
    public HashMap<String,Object> getChildConfig(String path) {
        Object o = data.get(path);
        if (!(o instanceof HashMap)) return null;
        return ((HashMap) o);
    }
    private boolean samePath(String path, String p2) {
        return samePath(path.split("\\."),p2);
    }
    private boolean samePath(String[] path, String p2) {
        String[] keySpl = p2.split("\\.");
        if (keySpl.length < path.length) return false;
        for (int i = 0; i< path.length; i++) {
            if (!keySpl[i].equals(path[i])) return false;
        }
        return true;
    }
    public boolean hasValue(String path) {
        return data.containsKey(path);
    }
    public void set(String path, Object value) {
        if (value == null) {
            data.remove(path);
            return;
        }
        data.put(path, value);
    }
    public void saveData() {
        try {
            ObjectOutputStream write = new ObjectOutputStream(new FileOutputStream(file));
            write.writeObject(data);
            write.flush();
            write.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
