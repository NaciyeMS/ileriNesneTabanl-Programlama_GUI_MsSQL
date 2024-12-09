package util;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import dao.PersonelDAO;
import model.Personal;

public class ReusableMetods {
	public static JTable createTableWithData() {
        // Tablo modelini oluştur
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers
        (new String[]{"ID", "Name", "Address", "Gender", "Knowledge", "Subject"});
        // DAO'dan veri çek
        PersonelDAO dao = new PersonelDAO();
        List<Personal> personelList = dao.selectPersonelDatas();
        // Verileri modele ekle
        for (Personal personel : personelList) {
            model.addRow(new Object[]{
                    personel.getId(),
                    personel.getName(),
                    personel.getAddress(),
                    personel.getGender(),
                    personel.getKnowledge(),
                    personel.getSubject()
            });
        }
        // JTable oluştur ve model ata
        JTable table = new JTable(model);
        return table; // Doldurulmuş JTable geri döndür
    }
}
