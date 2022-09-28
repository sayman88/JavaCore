package work8;

import java.sql.*;

public class WeatherDB {
    private static final String CONNECT_DB = "org.sqlite.JDBC";
    private static final String DB_PATH = "jdbc:sqlite:C:\\sql\\sqlite\\weather.db";
    private static final String SQL_CHECK = "SELECT dat,city from \"weatherDataOneDay\" order by id desc ";
    private static final String COLUMN_LABEL_DATE = "dat";
    private static final String COLUMN_LABEL_CITY = "city";
    private static final String SQL_INSERT_DATA = "insert into \"weatherDataOneDay\" " +
            "(city,dat,minT,maxT,dayDesc,dayPrecip,nightDesc,nightPrecip, option) values (?,?,?,?,?,?,?,?,?)";
    private static final String SQL_INSERT_CITY = "insert into \"weatherDataOneDay\" (city) values (?)";
    private static final String SQL_PRINT_DATA = "select city as Город, dat as дата, minT as мин_температура, maxT as макс_температура, " +
            "dayDesc as днём, dayPrecip as осадки_днем, nightDesc as ночью,nightPrecip as осадки_ночью, " +
            "option as примечание from \"weatherDataOneDay\" order by id desc";

    {
        try {
            Class.forName(CONNECT_DB);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveWeatherDB(String cityName, String[] weatherForecastOneDayArray) throws SQLException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_PATH);

            Statement statement = connection.createStatement();
            ResultSet resultSetCheck = statement.executeQuery(SQL_CHECK);
            if (!resultSetCheck.getString(COLUMN_LABEL_DATE).equals(weatherForecastOneDayArray[0])) {
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_DATA);
                for (int i = 0; i < weatherForecastOneDayArray.length; i++) {
                    preparedStatement.setString((i + 2), weatherForecastOneDayArray[i]);
                }
                connection.prepareStatement(SQL_INSERT_CITY);
                preparedStatement.setString(1, cityName);
                preparedStatement.addBatch();
                preparedStatement.executeBatch();
            }

            ResultSet resultSetPrint = statement.executeQuery(SQL_PRINT_DATA);
            for (int i = 1; i < weatherForecastOneDayArray.length; i++) {
                System.out.print(resultSetPrint.getMetaData().getColumnLabel(i) + " " + resultSetPrint.getString(i) + "\n");
            }
            System.out.println();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                assert connection != null;
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void saveWeatherFiveDayDB(String cityName, String[][] weatherForecastFiveDayArray) throws SQLException {
        Connection connection = null;
        for (String[] strings : weatherForecastFiveDayArray) {
            try {
                connection = DriverManager.getConnection(DB_PATH);
                Statement statement = connection.createStatement();

                ResultSet resultSetCheck = statement.executeQuery(SQL_CHECK);
                if (!resultSetCheck.getString(COLUMN_LABEL_DATE)
                        .equals(weatherForecastFiveDayArray[weatherForecastFiveDayArray.length - 1][0].split(",")[0])) {
                    PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_DATA);
                    for (int j = 0; j < 8; j++) {
                        preparedStatement.setString((j + 2), strings[0].split(",")[j]);
                    }

                    connection.prepareStatement(SQL_INSERT_CITY);
                    preparedStatement.setString(1, cityName);
                    preparedStatement.addBatch();
                    preparedStatement.executeBatch();
                }
                ResultSet resultSetPrint = statement.executeQuery(SQL_PRINT_DATA);
                for (int j = 1; j <= 8; j++) {
                    System.out.print(resultSetPrint.getMetaData().getColumnLabel(j) + " " + resultSetPrint.getString(j) + "\n");
                }
                System.out.println();

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    assert connection != null;
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
}