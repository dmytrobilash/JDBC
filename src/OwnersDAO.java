import javax.swing.tree.RowMapper;
import java.util.*;
import java.sql.*;

public class OwnersDAO implements Dao<Owners> {

    private final Connection connection;

    public OwnersDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Owners> get(long id) {
        try (Statement statement = connection.createStatement()) {
            var rs = statement.executeQuery(sql.GET_ALL.QUERY);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Owners> getAll(Map<String, Object> filter) {
        return getOwners();
    }

    private List<Owners> getOwners() {
        try (Statement statement = connection.createStatement()) {
            var rs = statement.executeQuery(sql.GET_ALL.QUERY);
            var resultList = new ArrayList<Owners>();
            while (rs.next()) {
                resultList.add(ResultSetToOwners(rs));
            }
            return resultList;
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }

    public List<Owners> getOwnersByCarModel(String model) {
        try (PreparedStatement statement = connection.prepareStatement(sql.GET_OWNER_BY_CAR_MODEL.QUERY)) {
            statement.setString(1, model);
            var rs = statement.executeQuery();
            var resultSet = new ArrayList<Owners>();
            while (rs.next()) {
                resultSet.add(ResultSetToOwners(rs));
            }
            return resultSet;


        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<Owners> getAll() {
        return getOwners(); // duplicate code
    }

    @Override
    public boolean save(Owners owners) {
        try (PreparedStatement statement = connection.prepareStatement(sql.INSERT.QUERY)) {
            FillPreparedStatement(owners, statement);
            //statement.setInt(1, owners.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean update(Owners owners) {
        try (PreparedStatement statement = connection.prepareStatement(OwnersDAO.sql.UPDATE.QUERY)) {
            FillPreparedStatement(owners, statement);
            statement.setInt(5, owners.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean delete(Owners owners) {
        try (PreparedStatement statement = connection.prepareStatement(sql.DELETE.QUERY)) {
            statement.setInt(0, owners.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    private static void FillPreparedStatement(Owners owners, PreparedStatement statement) throws SQLException {
        statement.setString(1, owners.getFname());
        statement.setString(2, owners.getLname());
        statement.setInt(3, owners.getAge());
        statement.setString(4, owners.getSex());
    }

    private static Owners ResultSetToOwners(ResultSet rs) throws SQLException {
        return new Owners(
                rs.getInt("id"),
                rs.getString("fname"),
                rs.getString("lname"),
                rs.getInt("age"),
                rs.getString("sex"));
    }

    private enum sql {
        GET_ALL("SELECT * FROM Owners"),
        GET_ONE("SELECT * FROM Owners WHERE id = ?"),
        INSERT("INSERT INTO Owners (fname, lname, age, sex) VALUES (?, ?, ?, ?)"),
        UPDATE("UPDATE Owners SET fname = ?, lname = ?, age = ?, sex = ? WHERE id = ?"),
        DELETE("DELETE FROM Owners WHERE id = ?"),
        GET_OWNER_BY_CAR_MODEL("SELECT * FROM owners INNER JOIN cars ON owners.id = cars.owner_id WHERE cars.model = ?;");


        final String QUERY;

        sql(String QUERY) {
            this.QUERY = QUERY;
        }
    }
}
