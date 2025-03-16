package capers;

import java.io.File;

public class test {
    public static void main(String[] args) {
        /** Current Working Directory. */
        final File CWD = new File(System.getProperty("user.dir"));

        /** Main metadata folder. */
        final File CAPERS_FOLDER = Utils.join(CWD, ".capers");

        Dog testDog = new Dog("testDog", "testBreed", 1);
        Utils.writeObject(Utils.join(CAPERS_FOLDER, "dogs", "testDog"), testDog);


    }
}
