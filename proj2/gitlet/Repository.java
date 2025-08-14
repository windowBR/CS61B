package gitlet;

import java.io.File;
import java.io.Serializable;

import static gitlet.Utils.*;

// TODO: any imports you need here

/** Represents a gitlet repository.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author windowbr
 */
public class Repository implements Serializable {
    /**
     * TODO: add instance variables here.
     *
     * List all instance variables of the Repository class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided two examples for you.
     */

    /** The Master pointer */
    public  Commit Master;
    /** The current HEAD pointer */
    public Commit HEAD;
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
        new File(GITLET_DIR, "object").mkdir();
        Commit firstCommit = new Commit("null", "initial commit");
        submitCommit(firstCommit);
    }

    public static void submitCommit(Commit commit) {
        String sha1code = commit.getCommitHash();
        File directory = join(GITLET_DIR, "object", sha1code.substring(0, 2));
        File fileName = join(directory, sha1code);
        // 判断是否存在前两个字符的文件夹
        if (!(directory.exists())) {
            directory.mkdir();
        }
        /**
         * TODO: 修bug
         * java gitlet.Main init
         * Exception in thread "main" java.lang.IllegalArgumentException: cannot overwrite directory
         *         at gitlet.Utils.writeContents(Utils.java:121)
         *         at gitlet.Utils.writeObject(Utils.java:157)
         *         at gitlet.Repository.submitCommit(Repository.java:54)
         *         at gitlet.Repository.init(Repository.java:44)
         *         at gitlet.Main.main(Main.java:22)
         */

        writeObject(fileName, commit);
    }
}
