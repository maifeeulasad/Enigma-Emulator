import enigma.ui.MFrameBuilder;

import java.util.List;

public class EnigmaMachine {

    private static final List<String> rotors = List.of("EKMFLGDQVZNTOWYHXUSPAIBRCJ",
            "AJDKSIRUXBLHWTMCQGZNPYFVOE",
            "BDFHJLCPRTXVZNYEIWGAKMUSQO",
            "ESOVPZJAYQUIRHXLNFTGKDCMWB",
            "VZBRGITYUPSDNHLXAWMJQOFECK",
            "JPGVOUMFYQBENHZRDKASXLICTW",
            "NZJHGRCXMYSWBOUFAIVLPEKQDT",
            "FKQHTLXOCBJSPDZRAMEWNIUYGV");

    private static final List<String> reflectors = List.of("ABCDEFGHIJKLMMLKJIHGFEDCBA",
            "BACDEFGHIJKLMMLKJIHGFEDCBA",
            "ABCFGIHJKLMMLKJIHGFEEDDCBA");

    public static void main(String[] args){
        new MFrameBuilder()
                .setTitle("Enigma - 17701086 - Maifee Ul Asad")
                .setFullScreen()
                .addRotors(rotors)
                .addReflectors(reflectors)
                .build();
    }

}
