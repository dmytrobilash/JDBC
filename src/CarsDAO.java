import java.sql.*;
import java.util.*;


public class CarsDAO implements Dao<Cars> {

    private final Connection connection;

    public CarsDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Cars> get(long id) {
        try (PreparedStatement statement = connection.prepareStatement(sql.GET_ONE.QUERY)) {
            statement.setLong(1, id);
            var resultSet = statement.executeQuery();
            resultSet.next();
            return Optional.of(ResultSetToCars(resultSet));
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public List<Cars> getAll() {
        return getCars();
    }

    @Override
    public List<Cars> getAll(Map<String, Object> filter) {
        return getCars();
    }

    private List<Cars> getCars() {
        try (Statement statement = connection.createStatement()) {
            var rs = statement.executeQuery(sql.GET_ALL.QUERY);
            var resultList = new ArrayList<Cars>();
            while (rs.next()) {
                resultList.add(ResultSetToCars(rs));
            }
            return resultList;
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public boolean save(Cars cars) {
        try (PreparedStatement statement = connection.prepareStatement(sql.INSERT.QUERY)) {
            FillPreparedStatement(cars, statement);
            //statement.setInt(7, cars.getOwnerId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean update(Cars cars) {
        try (PreparedStatement statement = connection.prepareStatement(sql.UPDATE.QUERY)) {
            FillPreparedStatement(cars, statement);
            //statement.setInt(6, cars.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }


    @Override
    public boolean delete(Cars cars) {
        try (PreparedStatement statement = connection.prepareStatement(sql.DELETE.QUERY)) {
            statement.setInt(1, cars.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    private static Cars ResultSetToCars(ResultSet rs) throws SQLException {
        return new Cars(
                rs.getInt("id"),
                rs.getString("manufacturer"),
                rs.getString("model"),
                rs.getInt("year_of_producing"),
                rs.getString("color"),
                rs.getDouble("price"),
                rs.getInt("ownerId"));
    }

    private void FillPreparedStatement(Cars cars, PreparedStatement statement) throws SQLException {
        statement.setString(1, cars.getManufacturer());
        statement.setString(2, cars.getModel());
        statement.setInt(3, cars.getYearOfProducing());
        statement.setString(4, cars.getColor());
        statement.setDouble(5, cars.getPrice());
        statement.setInt(6, cars.getOwnerId());

    }

    private enum sql {
        GET_ONE("SELECT * FROM cars WHERE id = ?"),
        GET_ALL("SELECT * FROM cars"),
        INSERT("INSERT INTO cars (manufacturer, model, year_of_producing, color, price, owner_id) VALUES (?, ?, ?, ?, ?, ?)"),
        UPDATE("UPDATE cars SET manufacturer = ?, model = ?, year_of_producing = ?, color = ?, owner_id = ? WHERE id = ?"),
        DELETE("DELETE FROM cars WHERE id = ?");

        final String QUERY;

        sql(String QUERY) {
            this.QUERY = QUERY;
        }

    }
}
