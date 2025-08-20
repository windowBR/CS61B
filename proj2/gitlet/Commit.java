package gitlet;

// TODO: any imports you need here

import java.io.Serializable;
import java.util.Date; // TODO: You'll likely use this in this class
import java.util.HashSet;
import java.util.Set;

/** Represents a gitlet commit object.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author TODO
 */
public class Commit implements Serializable {
    /**
     * TODO: add instance variables here.
     *
     * List all instance variables of the Commit class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided one example for `message`.
     */
    private final Date timestamp;
    /** The message of this Commit. */
    private final String message;
    private final String parentId;
    private Set<String> blobsId = new HashSet<>();

    /* TODO: fill in the rest of this class. */

    public Commit(String parent, String message) {
        if (parent.equals("null")) {
            this.timestamp = new Date(0);
        }else {
            this.timestamp = new Date();
        }
        this.parentId = parent;
        this.message = message;
    }

    public String getCommitHash() {
        StringBuilder commitInfo = new StringBuilder();
        commitInfo.append(this.parentId);
        commitInfo.append(this.timestamp);
        commitInfo.append(this.message);
        if (blobsId == null) {
            commitInfo.append("null");
        }else {
            for (String str: blobsId) {
                commitInfo.append(str);
            }
        }
        return Utils.sha1(commitInfo.toString());
    }
}
