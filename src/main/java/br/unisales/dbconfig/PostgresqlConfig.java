package br.unisales.dbconfig;

public final class PostgresqlConfig {
    private static final String URL = "jdbc:postgresql://localhost:5432/biblioteca_java";
    private static final String USER = "postgres";
    private static final String PASSWORD = "950915";

    public static String getUrl() {
        return URL;
    }

    public static String getUser() {
        return USER;
    }

    public static String getPassword() {
        return PASSWORD;
    }
}
