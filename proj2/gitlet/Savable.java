package gitlet;
import java.io.Serializable;

/**
 * Gitlet 项目中所有可保存对象的接口
 * @author windowbr
 */
public interface Savable extends Serializable {
    String getSha1();
}
