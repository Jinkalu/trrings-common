package com.triings.trringscommon.utils;

import org.springframework.http.MediaType;

import java.security.SecureRandom;

import static com.triings.trringscommon.utils.Constants.RANDOM_CHARACTER_SET;
import static com.triings.trringscommon.utils.Constants.RANDOM_STRING_LENGTH;

public class CommonUtils {
    public static MediaType getContentType(String filename) {
        String[] fileArrSplit = filename.split("\\.");
        String fileExtension = fileArrSplit[fileArrSplit.length - 1];
        return switch (fileExtension) {
//            case "txt" -> MediaType.TEXT_PLAIN;
            case "png" -> MediaType.IMAGE_PNG;
            case "jpg", "jpeg" -> MediaType.IMAGE_JPEG;
//            case "pdf" -> MediaType.APPLICATION_PDF;
            case "gif" -> MediaType.IMAGE_GIF;
            case "mp4","mov"->  MediaType.APPLICATION_OCTET_STREAM;
            default -> null;
        };
    }

    public static String getFileExtension(MediaType mediaType) {
        if (mediaType.equals(MediaType.TEXT_PLAIN)) {
            return ".txt";
        } else if (mediaType.equals(MediaType.IMAGE_PNG)) {
            return ".png";
        } else if (mediaType.equals(MediaType.IMAGE_JPEG)) {
            return ".jpg";
        } else if (mediaType.equals(MediaType.APPLICATION_PDF)) {
            return ".pdf";
        }else if (mediaType.equals(MediaType.APPLICATION_OCTET_STREAM)) {
            return ".mp4";
        }else if (mediaType.equals(MediaType.IMAGE_GIF)) {
            return ".gif";
        }
        return "";
    }

    public static String generateRandomString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < RANDOM_STRING_LENGTH; i++) {
            int randomInt = new SecureRandom().nextInt(RANDOM_CHARACTER_SET.length());
            sb.append(RANDOM_CHARACTER_SET.substring(randomInt, randomInt + 1));
        }
        return sb.toString();

    }
}
