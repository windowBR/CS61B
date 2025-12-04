package gitlet;
import java.io.Serializable;

/**
 * Gitlet 项目中所有可保存对象的接口
 * @author windowbr
 */
public interface Savable extends Serializable {
    /**
     * 获取对象的 sha1 值
     * @return sha1 字符串
     */
    String getSha1();
}
