package gitlet;

import java.io.File;

import static gitlet.Utils.*;

public class Blob implements Savable {
    private final byte[] contents;
    private final String sha1;

    public Blob(File file) {
        this.contents = readContents(file);
        this.sha1 = sha1((Object) contents);
    }

    /**
     * 返回 Blob 对应文件内容的 sha1
     * @return 文件内容的 sha1
     */
    public String getSha1() {
        return sha1;
    }

    public byte[] getContents() {
        return contents.clone();
    }
}
