import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.OptionalDouble;

class ExtensionFilter implements FilenameFilter {
    private final String extension;

    public ExtensionFilter(String extension) {
        this.extension = extension;
    }

    @Override
    public boolean accept(File dir, String name) {
        return name.endsWith(extension);
    }
}

public class HW01_1 {
    public static OptionalDouble calculateAverageFileSize(File[] files) {
        return Arrays.stream(files)
                .mapToLong(File::length)
                .average();
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide a directory path as an argument.");
            return;
        }

        Path directoryPath = Paths.get(args[0]);
        if (!Files.exists(directoryPath) || !Files.isDirectory(directoryPath)) {
            System.out.println("Directory does not exist or is not a directory.");
            return;
        }

        File directory = directoryPath.toFile();
        FilenameFilter filter = new ExtensionFilter(".txt");
        File[] filteredFiles = Optional.ofNullable(directory.listFiles(filter)).orElse(new File[0]);

        if (filteredFiles.length == 0) {
            System.out.println("No .txt files found in the directory.");
            return;
        }

        OptionalDouble averageSize = calculateAverageFileSize(filteredFiles);
        averageSize.ifPresentOrElse(
                avg -> System.out.printf("Average file size: %.2f bytes%n", avg),
                () -> System.out.println("Could not calculate the average file size.")
        );
    }
}
