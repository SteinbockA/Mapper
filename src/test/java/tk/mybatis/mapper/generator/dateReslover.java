package tk.mybatis.mapper.generator;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * Created by duanyixiao on 2018/5/30.
 */
public class dateReslover {
    private static final String path = "/Users/duanyixiao/IdeaProjects/personal/mybatis-generator-EXT/src/test/java/test/model/";

    public static void main(String[] args) throws Exception {
        String className = "Tbmessageconfig";
//        Class tclass = null;
        Path originPath = Paths.get(path + className + ".java");
        List<String> list = Files.readAllLines(originPath);
        for (int i = 0; i < list.size(); i++) {
            String thisline = list.get(i);
            if (thisline.contains("Date")) {
                if (thisline.contains("private")) {
                    String priorLine = list.get(i - 1);
                    String firstReplace = priorLine.replace("Column", "ColumnType");
                    String secondReplace = firstReplace.replaceFirst("name", "column");
                    String resStr = secondReplace.replace(")", ", typeHandler = TimeTypeHandler.class)");
                    list.set(i - 1, resStr);
                    list.set(i, thisline.replaceFirst("Date", "Long"));
                }
            }
        }
        Files.delete(originPath);
        Files.createFile(originPath);
        for (String s : list) {
            Files.write(originPath, (s + "\n").getBytes(), StandardOpenOption.APPEND);
        }
    }
}
