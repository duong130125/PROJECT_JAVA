package ra.edu.utils;

import java.util.List;

public interface AppService<T> {
    List<T> findAll();

    boolean save(T t);

    boolean update(T t);

    boolean delete(T t);
}
