package gitlet;

import java.io.File;
import java.nio.file.Path;

import static gitlet.Utils.*;

public class Blob implements Savable {
    private final Path filePath;
    private final byte[] contents;
    private final String sha1;


    /**
     * 该构造器接收两个参数，分别为：<br>
     * 1. 文件路径（Path）<br>
     * 2. 文件内容（由 Utils.readContents 方法获取的 byte[]），<br>
     * <p>
     * 将接收到的路径和内容直接存储在实例变量中，<br>
     * 并通过 Utils.sha1 计算文件内容的 sha1 值并存储。
     * </p>
     * @param filePath 相对于仓库根目录的文件路径
     * @param contents 文件内容，由 Utils.readContents 方法获取
     */
    public Blob(Path filePath, byte[] contents) {
        this.filePath = filePath;
        this.contents = contents;
        this.sha1 = sha1((Object) contents);
    }

    /**
     * 返回 Blob 对应文件内容的 sha1
     * @return 文件内容的 sha1
     */
    public String getSha1() {
        return sha1;
    }

    /**
     * 返回文件的绝对路径
     * @return 文件绝对路径字符串
     */
    public Path getFilePath() {
        return filePath;
    }

    public byte[] getContents() {
        return contents.clone();
    }
}
