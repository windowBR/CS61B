package gitlet;

import java.io.File;

import static gitlet.Utils.*;

public class Blob implements Savable {
    private byte[] contents;
    private String sha1;

    public Blob(File file) {
        this.contents = readContents(file);
        this.sha1 = sha1(contents);
    }

    public String getSha1() {
        return sha1;
    }

    public byte[] getContents() {
        return contents.clone();
    }
}
