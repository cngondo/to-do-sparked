import org.sql2o.*;

public class DB{
	private static String USER = "sensei";
	private static String PASSWORD = "nomasana";
	public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/to_do", USER, PASSWORD);
}