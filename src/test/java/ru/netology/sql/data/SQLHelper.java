package ru.netology.sql.data;


import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {

    private static QueryRunner runner = new QueryRunner();

    @SneakyThrows
    public static Connection getConn() {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/app", "masha", "mamamylaramu"
        );
    }

    @SneakyThrows
    public static DataHelper.AuthInfo getAuthInfo() {

        var dataSQL = "SELECT * FROM users ORDER BY id";
        try (var conn = getConn()) {
            var result = runner.query(getConn(), dataSQL, new BeanListHandler<>(DataHelper.AuthInfo.class));
            return new DataHelper.AuthInfo(result.get(0).getId(), result.get(0).getLogin(), result.get(0).getPassword(), result.get(0).getStatus());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @SneakyThrows
    public static DataHelper.VerificationInfo getVerificationCode() {

        var dataSQL = "SELECT * FROM auth_codes ORDER BY created DESC";
        try (var conn = getConn()) {
            var result = runner.query(getConn(), dataSQL, new BeanListHandler<>(DataHelper.VerificationInfo.class));
            return new DataHelper.VerificationInfo(result.get(0).getCode(), result.get(0).getUserId());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @SneakyThrows
    public static void cleanDataBase() {
        var conn = getConn();
        runner.execute(getConn(), "DELETE FROM auth_codes");
        runner.execute(getConn(), "DELETE FROM card_transactions");
        runner.execute(getConn(), "DELETE FROM cards");
        runner.execute(getConn(), "DELETE FROM users");

    }
}
