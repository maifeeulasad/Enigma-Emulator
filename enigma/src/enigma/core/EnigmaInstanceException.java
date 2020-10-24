package enigma.core;

public class EnigmaInstanceException extends RuntimeException {
    EnigmaInstanceException(){
        super("Enigma instance is singleton\n" +
                " - instance not created yet\n" +
                " - trying to create again, to create again reload the application");
    }
}
