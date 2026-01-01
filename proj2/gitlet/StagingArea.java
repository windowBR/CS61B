package gitlet;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class StagingArea implements Savable {
    // 存储暂存区的 Blob，键值对为 "Path:Blob_sha1"
    private Map<Path,String> stagingArea = new HashMap<>();

    public void addBlob(Blob blob) {
        stagingArea.put(blob.getFilePath(), blob.getSha1());
    }



    // TODO: 完成 removeBlob 方法
    public void removeBlob(){}

    @Override
    public String getSha1() {
        // TODO: 完成 StagingArea 的 sha1
        return "";
    }
}
