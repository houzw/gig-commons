package cn.gig.rs.commons.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * description:
 *
 * @author houzhiwei
 * @date 2022/8/7/0007 23:14
 */
public class FileUtils {

    /**
     * list all files in a given directory and all of its sub-directories.
     *
     * @param dir                the given directory
     * @param filenameFilters    filename filter conditions
     * @param filenameFilterEnum how to check file name: startWith, endWith, contains
     * @return all file paths
     * @throws IOException
     */
    public static List<Path> listFiles(String dir, String[] filenameFilters, FilenameFilterEnum filenameFilterEnum) throws IOException {
        try (Stream<Path> stream = Files.walk(Paths.get(dir))) {
            return stream.map(Path::normalize)
                    .filter(Files::isRegularFile)
                    .filter(path -> {
                        boolean b = false;
                        for (String filter : filenameFilters) {
                            switch (filenameFilterEnum) {
                                case END_WITH:
                                    b = path.getFileName().toString().toLowerCase().endsWith(filter.toLowerCase());
                                    break;
                                case START_WITH:
                                    b = path.getFileName().toString().toLowerCase().startsWith(filter.toLowerCase());
                                    break;
                                case CONTAINS:
                                default:
                                    b = path.getFileName().toString().toLowerCase().contains(filter.toLowerCase());
                            }
                            if (b) {
                                return b;
                            }
                        }
                        return b;
                    }).collect(Collectors.toList());
        }
    }

    public enum FilenameFilterEnum {
        START_WITH, END_WITH, CONTAINS
    }
}
