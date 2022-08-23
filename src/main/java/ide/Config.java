package ide;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import static ide.Ide.configPath;

public class Config {
    private LinkedHashMap<String, String[]> config;

    public static Config fromFile(String path) throws IOException {
        String text = Files.readString(Path.of(path));
        new File(configPath.substring(0, configPath.lastIndexOf('/'))).mkdir();
        Config ret = new Config();
        //Config file format: key | key=value1,value2,value3

        for (String line : text.split("\n")) {
            if (line.isEmpty())
                continue;

            if (line.contains("=")) {
                String[] split = line.split("=");

                if (split.length != 2)
                    ret.config.put(split[0], new String[0]);
                else
                    ret.config.put(split[0], split[1].split(","));
            } else
                ret.config.put(line, new String[0]);
        }

        return ret;
    }

    public void writeToFile(Path path) {
        //Config file format: key | key=value1,value2,value3
        try {
            Files.writeString(path, config.keySet().stream().map(key -> key + "=" + Arrays.stream(config.get(key)).reduce((a, b) -> a + "," + b).orElse("")).reduce((a, b) -> a + "\n" + b).orElse(""));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Config() {
        config = new LinkedHashMap<>();
    }

    public void addOption(String name) {
        config.put(name, new String[0]);
    }

    public void addOption(String name, String... args) {
        config.put(name, args);
    }

    public boolean hasOption(String name) {
        return config.containsKey(name);
    }

    public String[] getOption(String name) {
        return config.get(name);
    }

    public void editOption(String name, String... args) {
        if (config.containsKey(name))
            config.replace(name, args);
        else
            config.put(name, args);
    }

    public void removeOption(String name) {
        config.remove(name);
    }
}
