package gitlet;

import java.io.File;
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

    private Map<String, String> stagingArea = new HashMap<>(); // 键值对为 对应文件路径:Blob_sha1
    private Set<String> commits = new HashSet<>(); // 存储所有 commit 的 sha1
    private Commit Master;
    private Commit HEAD;

    /** The current working directory. */
    public static final File CWD = new File(System.getProperty("user.dir"));
    /** The .gitlet directory. */
    public static final File GITLET_DIR = join(CWD, ".gitlet");

    /* TODO: fill in the rest of this class. */
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
        repo.submitCommit(firstCommit);
        repo.commits.add(firstCommit.getSha1());

        // 初始化 master 和 HEAD
        repo.Master = firstCommit;
        repo.HEAD = firstCommit;

        // 保存 repo 状态
        saveRepo(repo);
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
        Blob blob = new Blob(file);
    }


    public void submitCommit(Commit commit) {
        saveObject(commit);
    }

    public void saveObject(Savable obj) {
        String sha1code = obj.getSha1();
        File directory = join(GITLET_DIR, "object", sha1code.substring(0, 2));
        File fileName = join(directory, sha1code.substring(2));
        // 判断是否存在前两个字符的文件夹
        if (!(directory.exists())) {
            directory.mkdir();
        }
        // TODO: 删除调试信息
        System.out.println("Debug: obj sha1code is " + sha1code);
        writeObject(fileName, obj);
    }

    static void saveRepo(Repository repo) {
        writeObject(join(GITLET_DIR, "repo"), repo);
    }

    @Override
    public String getSha1() {
        // TODO: 拼接 Repository 各字段并返回 sha1
        return sha1(this);
    }
}
