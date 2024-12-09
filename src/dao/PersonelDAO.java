package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Personal;
import util.DatabaseConnection;

public class PersonelDAO {
	// Personel ekleme
    public void savePersonel(Personal personel) {
        String sql = "INSERT INTO personel (name, adress, gender, knowlegde, subject) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {            
            statement.setString(1, personel.getName());
            statement.setString(2, personel.getAddress());
            statement.setString(3, personel.getGender());
            statement.setString(4, personel.getKnowledge());
            statement.setString(5, personel.getSubject());            
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Personel eklerken hata: " + e.getMessage());
        }
    }

    // Personel bilgilerini ID ile arama
    public Personal findById(int id) {
        String sql = "SELECT * FROM personel WHERE id = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Personal personel = new Personal(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getString("address"),
                            resultSet.getString("gender"),
                            resultSet.getString("knowledge"),
                            resultSet.getString("subject")
                    );
                    return personel;
                }
            }
        } catch (SQLException e) {
            System.err.println("Personel ararken hata: " + e.getMessage());
        }
        return null; // Personel bulunamadı
    }

    // Personel bilgilerini güncelleme
    public void updatePersonel(Personal personel) {
        String sql = "UPDATE personel SET name = ?, address = ?, gender = ?, knowledge = ?, subject = ? WHERE id = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setString(1, personel.getName());
            statement.setString(2, personel.getAddress());
            statement.setString(3, personel.getGender());
            statement.setString(4, personel.getKnowledge());
            statement.setString(5, personel.getSubject());
            statement.setInt(6, personel.getId());
            
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Personel güncellenirken hata: " + e.getMessage());
        }
    }

	public List<Personal> selectPersonelDatas() {
		String sql = "SELECT * FROM personel";
        List<Personal> personelList = new ArrayList<>();
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {            
            while (resultSet.next()) {
                Personal personel = new Personal(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("adress"),
                        resultSet.getString("gender"),
                        resultSet.getString("knowlegde"),
                        resultSet.getString("subject")
                );
                personelList.add(personel);
            }
        } catch (SQLException e) {
            System.err.println("Personel bilgileri alınırken hata: " + e.getMessage());
        }        
        return personelList;
	}
}
