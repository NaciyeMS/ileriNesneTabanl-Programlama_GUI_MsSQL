package util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseConnection {
	// MSSQL veritabanı bağlantısı için gerekli bilgiler
    private static final String DB_URL = 
    		"jdbc:sqlserver://localhost:1433;databaseName=guiDb;encrypt=true;trustServerCertificate=true;";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "sa123456*-";
    private static Connection connection = null;
    // Bağlantı oluşturma metodu
    public static Connection getConnection() {
    	   try {
    	        // Eğer bağlantı null ya da kapalıysa yeniden oluştur
    	        if (connection == null || connection.isClosed()) {
    	            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    	            System.out.println("MSSQL Veritabanına bağlantı başarılı!");
    	        }
    	    } catch (SQLException e) {
    	        System.out.println("MSSQL Veritabanına bağlantı sırasında bir hata oluştu!");
    	        e.printStackTrace();
    	    }
    	    return connection;
    	
    }

    // Bağlantıyı kapatma metodu
    public static void closeConnection() {
         if (connection != null) {
            try {
                connection.close();
                System.out.println("MSSQL Veritabanı bağlantısı kapatıldı.");
            } catch (SQLException e) {
                System.out.println("Bağlantı kapatma sırasında bir hata oluştu!");
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // Test için bağlantıyı kontrol et
        Connection conn = DatabaseConnection.getConnection();
        DatabaseConnection.closeConnection();
    }
}


