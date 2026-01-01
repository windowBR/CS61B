package gitlet;

import java.io.File;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static gitlet.Utils.*;

// TODO: any imports you need here

/** Represents a gitlet repository.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author windowbr
 */
public class Repository implements Savable {
    // Main TODO: 切换到单独的 StagingArea 类实现
    private Map<String, String> stagingArea = new HashMap<>(); // 存储暂存区的 Blob，键值对为 "Path:Blob_sha1"
    private Set<String> commits = new HashSet<>(); // 以 sha1 形式存储所有已提交的 commit
    private Commit Master;
    private Commit HEAD;

    /** The current working directory. */
    public static final File CWD = new File(System.getProperty("user.dir"));
    /** The .gitlet directory. */
    public static final File GITLET_DIR = join(CWD, ".gitlet");

    /* TODO: fill in the rest of this class. */
    /**
     * 初始化一个新的 gitlet 版本库，<br>
     * 1. 先检测是否存在 .gitlet 目录，若存在则报错退出，<br>
     * 2. 否则创建 .gitlet 目录，创建 object 目录，创建初始 Commit 并保存，<br>
     * 3. 初始化 Master 和 HEAD 指针
     */
    public static void init() {
        // 若存在 .gitlet 目录则直接退出
        if (GITLET_DIR.exists()) {
            System.out.println("A Gitlet version-control system already exists in the current directory.");
            System.exit(0);
        }

        GITLET_DIR.mkdir();

        Repository repo = new Repository();
        // 创建 object 目录并提交第一个 Commit
        new File(GITLET_DIR, "object").mkdir();
        Commit firstCommit = new Commit(null, "initial commit");
        repo.saveObject(firstCommit);
        repo.commits.add(firstCommit.getSha1());

        // 初始化 master 和 HEAD
        repo.Master = firstCommit;
        repo.HEAD = firstCommit;

        // 保存 repo 状态
        saveRepo(repo);
    }

    /**
     * 提交暂存区的文件，生成新的 Commit 对象
     * <p>
     * 1. 读取暂存区 Blob，若为空则报错退出 <br>
     * 2. 创建新 Commit 对象，新对象应先复制父对象（即当前 HEAD）的 Blob 列表 <br>
     * 3. 使用暂存区的 Blob 覆盖同相同文件的 Blob <br>
     * 4. 存储新 Commit 对象，将 HEAD 指针指向新对象
     * </p>
     */
    public void commit() {
        // TODO: 完成 commit 方法

    }

    /**
     * 将文件添加到暂存区,仅支持单文件添加
     * @param filePath 文件路径的字符串
     */
    public void add(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("File does not exist.");
            System.exit(0);
        }

        // 计算文件相对于仓库根目录的路径，后续使用应以该路径为主
        Path relativePath = Repository.CWD.toPath()
                .toAbsolutePath()
                .relativize(file.toPath().toAbsolutePath());

        Blob blob = new Blob(relativePath, readContents(file));
        saveObject(blob);

        stagingArea.put(filePath, blob.getSha1());
        saveRepo(this);
        System.out.println("DEBUG: add() called - staging area status: " + stagingArea);
    }


    /**
     * 将传入的 object 序列化后写入到 .gitlet/object/ 目录下
     * @param obj 要存储的对象
     */
    private void saveObject(Savable obj) {
        String sha1code = obj.getSha1();
        // 构造存储路径，sha1 的前两个字符为文件夹名，后面为文件名
        File directory = join(GITLET_DIR, "object", sha1code.substring(0, 2));
        File fileName = join(directory, sha1code.substring(2));
        // 判断是否存在前两个字符的文件夹
        if (!(directory.exists())) {
            directory.mkdir();
        }
        // TODO: 删除调试信息
        System.out.println("DEBUG: saveObject() called \n type: " + obj.getClass().getName() + "sha1: " + sha1code);
        writeObject(fileName, obj);
    }

    /**
     * 将 Repository 对象写入到 .gitlet/repo 文件中
     * @param repo 要保存的 Repository 对象
     */
    private static void saveRepo(Repository repo) {
        writeObject(join(GITLET_DIR, "repo"), repo);
    }

    @Override
    public String getSha1() {
        // TODO: 拼接 Repository 各字段并返回 sha1
        return sha1(this);
    }
}
