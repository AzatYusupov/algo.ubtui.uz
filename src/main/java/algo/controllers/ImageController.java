package algo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;

/**
 * Created by AzatYusupov on 04.01.2018.
 */
@Controller
public class ImageController {

    @RequestMapping(value = "/userImage/{userId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<?> userImage(@PathVariable("userId") long userId) {
        try {
            File userImageFile = new File("avatars/" + userId + ".jpg");
            if (!userImageFile.exists())
                userImageFile = new File("avatars/" + 0 + ".jpg");
            return new ResponseEntity<>(Files.readAllBytes(userImageFile.toPath()), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
