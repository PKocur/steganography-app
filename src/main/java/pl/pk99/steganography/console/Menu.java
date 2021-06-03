package pl.pk99.steganography.console;

import com.diogonunes.jcolor.Attribute;
import pl.pk99.steganography.config.Configuration;
import pl.pk99.steganography.console.config.Colors;
import pl.pk99.steganography.console.config.MenuMessages;
import pl.pk99.steganography.console.config.Messages;
import pl.pk99.steganography.model.MessageDecoder;
import pl.pk99.steganography.model.MessageEncoder;
import pl.pk99.steganography.model.impl.SimpleMessageDecoder;
import pl.pk99.steganography.model.impl.SimpleMessageEncoder;
import pl.pk99.steganography.util.ImageUtil;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Scanner;

import static com.diogonunes.jcolor.Ansi.colorize;

public class Menu {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        showWelcomeMessage();
        showMenu();
    }

    private static void showWelcomeMessage() {
        addLineBreak();
        System.out.println(colorize(MenuMessages.WELCOME, Colors.LOGO_COLOR, Attribute.BOLD()));
        addLineBreak();
    }

    private static void showMenu() {
        addLineBreak();
        System.out.println(colorize(MenuMessages.HEADER, Colors.MENU_COLOR, Attribute.BOLD()));
        System.out.println(colorize(MenuMessages.ENCODE, Colors.MENU_COLOR, Attribute.BOLD()));
        System.out.println(colorize(MenuMessages.DECODE, Colors.MENU_COLOR, Attribute.BOLD()));
        System.out.println(colorize(MenuMessages.EXIT, Colors.MENU_COLOR, Attribute.BOLD()));
        System.out.println(colorize(MenuMessages.FOOTER, Colors.MENU_COLOR, Attribute.BOLD()));
        addLineBreak();

        System.out.println(colorize(Messages.ENTER_OPTION, Colors.MENU_RESPONSE_COLOR));
        byte markedOption;
        while (true) {
            markedOption = Byte.parseByte(SCANNER.nextLine());
            addLineBreak();
            switch (markedOption) {
                case 1:
                    encodeMessage(getImage());
                    showMenu();
                case 2:
                    decodeMessage(getImage());
                    showMenu();
                case 3:
                    System.exit(0);
                default:
                    System.out.println(colorize(Messages.INVALID_OPTION, Colors.ERROR_COLOR));
            }
        }
    }

    private static void encodeMessage(BufferedImage image) {
        MessageEncoder messageEncoder = new SimpleMessageEncoder();
        String message = getMessageOptionallyEncrypted(getMessage(), messageEncoder);
        int[] pixelsOfImage = image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth());
        messageEncoder.encodeToImage(message, pixelsOfImage);
        try {
            String outputFileName = getNameOfTheOutputImage();
            ImageUtil.saveImage(outputFileName + "." + Configuration.IMAGE_FORMAT, image, pixelsOfImage);
            System.out.println(colorize("Message successfully encoded into image! Saved into '" + outputFileName + "." + Configuration.IMAGE_FORMAT + "'.",
                    Colors.MENU_IMPORTANT_RESPONSE_COLOR));
        } catch (IOException e) {
            System.out.println(colorize(Messages.ERROR, Colors.ERROR_COLOR));
            encodeMessage(image);
        }
    }

    private static String getMessage() {
        System.out.println(colorize("Please provide a message:", Colors.MENU_RESPONSE_COLOR));
        return SCANNER.nextLine();
    }

    private static String getMessageOptionallyEncrypted(String message, MessageEncoder messageEncoder) {
        System.out.println(colorize("Do you want to encrypt the message? (y/n)", Colors.MENU_RESPONSE_COLOR));
        Boolean shouldEncrypt = getYesOrNoResponse();
        if (shouldEncrypt == null) {
            return getMessageOptionallyEncrypted(message, messageEncoder);
        } else if (shouldEncrypt) {
            return messageEncoder.encrypt(message, getEncryptionKey());
        }
        return message;
    }

    private static String getNameOfTheOutputImage() {
        System.out.println(colorize("Please provide name of the output image:", Colors.MENU_RESPONSE_COLOR));
        return SCANNER.nextLine();
    }

    private static void decodeMessage(BufferedImage image) {
        MessageDecoder messageDecoder = new SimpleMessageDecoder();
        String decodedMessage = "";
        try {
            decodedMessage = new String(
                    messageDecoder.decodeFromImage(image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth())));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(colorize("Cannot decode message from the image. Please try another one.", Colors.ERROR_COLOR));
            decodeMessage(getImage());
        }
        System.out.println(colorize("The decoded message is: ", Colors.MENU_RESPONSE_COLOR));
        System.out.println(colorize(decodedMessage, Colors.MENU_IMPORTANT_RESPONSE_COLOR));
        decodedMessage = getMessageOptionallyDecrypted(decodedMessage, messageDecoder);
        System.out.println(colorize("The final message is: ", Colors.MENU_RESPONSE_COLOR));
        System.out.println(colorize(decodedMessage, Colors.MENU_IMPORTANT_RESPONSE_COLOR));
    }

    private static String getMessageOptionallyDecrypted(String decodedMessage, MessageDecoder messageDecoder) {
        System.out.println(colorize("Do you want to decrypt the message? (y/n)", Colors.MENU_RESPONSE_COLOR));
        Boolean shouldDecrypt = getYesOrNoResponse();
        if (shouldDecrypt == null) {
            return getMessageOptionallyDecrypted(decodedMessage, messageDecoder);
        } else if (shouldDecrypt) {
            return messageDecoder.decrypt(decodedMessage, getEncryptionKey());
        }
        return decodedMessage;
    }

    private static BufferedImage getImage() {
        System.out.println(colorize("Please provide a file path to the image (only " + Configuration.IMAGE_FORMAT + " format available):", Colors.MENU_RESPONSE_COLOR));
        String path = SCANNER.nextLine();
        try {
            return ImageUtil.readImage(path);
        } catch (IOException e) {
            System.out.println(colorize("Invalid file path. Please choose another one.", Colors.ERROR_COLOR));
            return getImage();
        }
    }

    private static String getEncryptionKey() {
        System.out.println(colorize("Please provide an encryption key (only letters available):", Colors.MENU_RESPONSE_COLOR));
        return SCANNER.nextLine();
    }

    private static Boolean getYesOrNoResponse() {
        String markedOption = SCANNER.nextLine();
        switch (markedOption) {
            case "y":
                return true;
            case "n":
                return false;
            default:
                System.out.println(colorize(Messages.INVALID_OPTION, Colors.ERROR_COLOR));
                return null;
        }
    }

    private static void addLineBreak() {
        System.out.println("\n");
    }
}
