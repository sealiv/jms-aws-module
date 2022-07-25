package org.aleks4ay.jms.utils;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

import java.io.*;
import java.util.Scanner;

public class S3Utils {

    final static AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_1).build();

    public static String getFileFromS3asString(String bucketName, String fileName) {
        StringBuilder sb = new StringBuilder();
        if (!s3.doesObjectExist(bucketName, fileName)) {
            return "";
        }
        S3Object o = s3.getObject(bucketName, fileName);
        S3ObjectInputStream s3is = o.getObjectContent();
        Scanner scanner = new Scanner(s3is);
        while (scanner.hasNextLine()) {
            sb.append(scanner.nextLine()).append(System.lineSeparator());
        }
        scanner.close();
        return sb.toString();
    }

    public static String append(String file, String message) {
        return file + message + System.lineSeparator();
    }

    public static void putFileToS3(File file, String bucketName, String fileName) {
        try {
            s3.putObject(bucketName, fileName, file);
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
        }
    }

    public static File writeFile(String input)  {
        File file = new File("temp_file");
        try (FileWriter writer = new FileWriter(file)){
            writer.write(input);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
