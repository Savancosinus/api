package com.hardisgroup.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

@SpringBootApplication
@EnableSwagger2
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);

        File file = new File("mon-fichier0");
        String path = file.getAbsolutePath();
        System.out.println(path.toString());

        File f = new File("C:\\example");
        File[] matchingFiles = f.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.startsWith("temp") && name.endsWith("txt");
            }
        });
        System.out.println(Arrays.toString(matchingFiles));
    }
}
