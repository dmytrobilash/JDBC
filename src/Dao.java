import java.util.List;
import java.util.Map;
import java.util.Optional;

abstract public interface Dao<T> {

    abstract Optional<T> get(long id);

    abstract List<T> getAll();

    abstract List<T> getAll(Map<String, Object> filter);

    abstract boolean save(T t);

    abstract boolean update(T t);

    abstract boolean delete(T t);
}
