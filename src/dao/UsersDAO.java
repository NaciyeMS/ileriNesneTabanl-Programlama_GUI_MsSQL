package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;
import util.DatabaseConnection;
/*try-with-resources Ne İşe Yarar?
Kaynakları otomatik olarak kapatma: try-with-resources bloğu, 
kullanılan kaynakların (örneğin, veritabanı bağlantıları, 
dosya okuma/yazma stream'leri) otomatik olarak kapanmasını sağlar.
Bu, her kaynak için manuel olarak close() metodunun çağrılmasına
 gerek kalmadan işlemi gerçekleştirir.
Kodun daha temiz ve güvenli olması: try-with-resources kullanmak,
finally bloklarına olan ihtiyacı ortadan kaldırır,
bu da kodun daha okunabilir ve yönetilebilir olmasını sağlar.
try (Connection connection = DatabaseConnection.getConnection();
 PreparedStatement statement = connection.prepareStatement(sql)) 
 {
// SQL işlemleri burada yapılır
} catch (SQLException e) {
    // Hata işleme
}

*/
public class UsersDAO { 
	// Kullanıcı girişi (username ve password ile doğrulama)
    public User login(String username, String password) {
        String sql = "SELECT * FROM admin WHERE username = ? AND password = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {           
	            statement.setString(1, username); // Parametreleri set et
	            statement.setString(2, password); // Şifreyi hashlenmiş olarak kontrol etmeniz tavsiye edilir.
	            try (ResultSet resultSet = statement.executeQuery()) {
	                if (resultSet.next()) {
	                    User user = new User();
	                    user.setId(resultSet.getInt("sno"));
	                    user.setUsername(resultSet.getString("username"));
	                    user.setPassword(resultSet.getString("password"));	                  
	                    return user;
	                }
	            }
          } catch (SQLException e) {
            System.err.println("Giriş hatası: " + e.getMessage());
          }
        return null; // Kullanıcı bulunamadı
    }
    
    // Yeni kullanıcı kaydı ekleme
    public void saveUser(User user) {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword()); // Şifreyi burada hashleyin
            statement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Kullanıcı kaydederken hata: " + e.getMessage());
        }
    }

    // Kullanıcı adıyla arama
    public User findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setUsername(resultSet.getString("username"));
                    user.setPassword(resultSet.getString("password"));
                    return user;
                }
            }
        } catch (SQLException e) {
            System.err.println("Kullanıcı bulma hatası: " + e.getMessage());
        }
        return null;
    }
}
