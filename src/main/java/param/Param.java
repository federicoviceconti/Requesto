package param;

public interface Param<K, V, R> {
    V getParam(K param);
    V addParam(K param, V value);
    R buildParam();
    String getLength();
}
