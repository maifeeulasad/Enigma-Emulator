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

    private static final int[][] arrowCombination = {
            {1,20,4,6,7,9,2,4},
            {1,2,4,16,7,9,20,24},
            {2,18,4,26,17,9,20,14},
            {1,19,4,3,7,9,20,3},
            {1,21,14,2,22,9,20,4},
            {11,22,4,6,7,9,20,4},
            {1,2,4,1,7,9,1,1},
            {1,2,4,1,8,9,20,4},
    };

    private static final List<String> reflectors = List.of("ABCDEFGHIJKLMMLKJIHGFEDCBA",
            "BACDEFGHIJKLMMLKJIHGFEDCBA",
            "ABCFGIHJKLMMLKJIHGFEEDDCBA");

    public static void main(String[] args){
        new MFrameBuilder()
                .setTitle("Enigma - 17701086 - Maifee Ul Asad")
                .setFullScreen()
                .setRotors(rotors)
                .setReflectors(reflectors)
                .setArrowCombination(arrowCombination)
                .build();
    }

}
