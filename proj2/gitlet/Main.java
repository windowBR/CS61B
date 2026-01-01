package gitlet;

import java.io.File;


/** Driver class for Gitlet, a subset of the Git version-control system.
 *  @author windowbr
 */
public class Main {

    /** Usage: java gitlet.Main ARGS, where ARGS contains
     *  <COMMAND> <OPERAND1> <OPERAND2> ... 
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please enter a command.");
            System.exit(0);
        }

        String firstArg = args[0];
        switch(firstArg) {
            case "init":
                Repository.init();
                break;
            case "add":
                checkInitialized();
                if(args.length != 2) {
                    System.out.println("Incorrect operands.");
                    System.exit(0);
                }
                getRepo().add(args[1]);
                break;
            case  "commit":
                checkInitialized();
                // TODO: handle the `commit [message]` command
                break;
            case "rm":
                checkInitialized();
                // TODO: handle the `rm [filename]` command
                break;
            case "log":
                checkInitialized();
                // TODO: handle the `log` command
                break;
            case "global-log":
                checkInitialized();
                // TODO: handle the `global-log` command
                break;
            case "find":
                checkInitialized();
                // TODO: handle the `find [message]` command
                break;
            case "status":
                checkInitialized();
                // TODO: handle the `status` command
                break;
            case "checkout":
                checkInitialized();
                // TODO: handle the `checkout` command
                break;
            case "branch":
                checkInitialized();
                // TODO: handle the `branch [branch name]` command
                break;
            case "rm-branch":
                checkInitialized();
                // TODO: handle the `rm-branch [branch name]` command
                break;
            case "reset":
                checkInitialized();
                // TODO: handle the `reset [commit id]` command
                break;
            case "merge":
                checkInitialized();
                // TODO: handle the `merge [branch name]` command
                break;

            // TODO: FILL THE REST IN
            default:
                System.out.println("No command with that name exists.");
                System.exit(0);
                break;
        }
    }

    public static void checkInitialized() {
        File repoFile = Utils.join(Repository.GITLET_DIR, "repo");
        System.out.println("DEBUG: checkInitialized() called");
        System.out.println("DEBUG: !repoFile.exists() = " + !repoFile.exists());
        if (!repoFile.exists()) {
            System.out.println("Not in an initialized Gitlet directory.");
            System.exit(0);
        }
    }

    /** 若不存在 .gitlet 目录则打印 "Not in an initialized Gitlet directory." 并退出程序
     * @return .gitlet 目录中的 Repository 对象
     */
    public static Repository getRepo() {
        checkInitialized();
        try {
            return Utils.readObject(Utils.join(Repository.GITLET_DIR, "repo"), Repository.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}   
