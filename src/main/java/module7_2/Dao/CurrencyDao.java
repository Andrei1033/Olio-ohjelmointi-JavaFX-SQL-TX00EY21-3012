package module7_2.Dao;

import module7_2.Datasource.MariaDbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CurrencyDao {
    public double getExchangeRate(String currencyCode) {

        double exchangeRate = 0.0;
        String sql = "SELECT rate FROM currency WHERE code = ?";

        try {
            Connection connection = MariaDbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, currencyCode);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                exchangeRate = resultSet.getDouble("rate");
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return exchangeRate;
    }

    public Map<String, Double> getAllExchangeRates() {
        Map<String, Double> rates = new HashMap<>();
        String sql = "SELECT code, rate FROM currency";

        try {
            Connection connection = MariaDbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String code = resultSet.getString("code");
                double rate = resultSet.getDouble("rate");
                rates.put(code, rate);
            }

            if (resultSet.next()) {
                String code = resultSet.getString("code");
                double rate = resultSet.getDouble("rate");
                rates.put(code, rate);
            }

            if (rates.isEmpty()) {
                throw new RuntimeException("No currencies found in database");
            }

            return rates;

        }
        catch (SQLException e) {
            throw new RuntimeException("Database error: " + e.getMessage(), e);
        }
    }

    public  Map<String, Object[]> getAllCurrencyInfo() {
        Map<String, Object[]> currencyInfo = new HashMap<>();
        String sql = "SELECT code, name, rate FROM currency";

        try {
            Connection connection = MariaDbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String code = resultSet.getString("code");
                String name = resultSet.getString("name");
                double rate = resultSet.getDouble("rate");
                currencyInfo.put(code, new Object[]{name, rate});
            }

            if (currencyInfo.isEmpty()) {
                throw new RuntimeException("No currencies found in database");
            }

            return currencyInfo;

        }
        catch (SQLException e) {
            throw new RuntimeException("Database error: " + e.getMessage(), e);
        }
    }
}
