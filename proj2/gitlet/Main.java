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
        // TODO: what if args is empty?
        if (args.length == 0) {
            System.out.println("Please enter a command.");
            System.exit(0);
        }

        File repoFile = Utils.join(Repository.GITLET_DIR, "repo");
        if(repoFile.exists()) {
            Repository repo = loadRepo();
        }

        String firstArg = args[0];
        switch(firstArg) {
            case "init":
                // TODO: handle the `init` command
                Repository.init();
                break;
            case "add":
                // TODO: handle the `add [filename]` command
                break;
            // TODO: FILL THE REST IN
            default:
                System.out.println("No command with that name exists.");
                System.exit(0);
                break;
        }
    }

    public static Repository loadRepo() {
        try {
            return Utils.readObject(Utils.join(Repository.GITLET_DIR, "repo"), Repository.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}   
