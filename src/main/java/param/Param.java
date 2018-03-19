package param;

/**
 * This interface is used to construct params into a request
 * @param <K> Key of the param
 * @param <V> Value of the param
 */
public interface Param<K, V> {
    V getParam(K param);
    V addParam(K param, V value);
    String buildParam();
    String getLength();
}
