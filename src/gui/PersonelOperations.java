package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.PersonelDAO;
import model.Personal;
import util.ReusableMetods;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.ScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PersonelOperations extends JFrame {
	private JTable table;
    private DefaultTableModel tableModel;
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textName;
	private JTextField textAdress;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTable table_1;
	private int selectedId;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PersonelOperations frame = new PersonelOperations();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PersonelOperations() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 828, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(45, 36, 45, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Adress");
		lblNewLabel_1.setBounds(45, 71, 45, 13);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Gender");
		lblNewLabel_2.setBounds(45, 113, 45, 13);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Knowledge");
		lblNewLabel_3.setBounds(45, 151, 86, 13);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Subject");
		lblNewLabel_4.setBounds(45, 194, 45, 13);
		contentPane.add(lblNewLabel_4);
		
		textName = new JTextField();
		textName.setBounds(114, 36, 160, 19);
		contentPane.add(textName);
		textName.setColumns(10);
		
		textAdress = new JTextField();
		textAdress.setBounds(114, 71, 160, 19);
		contentPane.add(textAdress);
		textAdress.setColumns(10);
		
		JRadioButton rdbtnGenderMale = new JRadioButton("Male");
		buttonGroup.add(rdbtnGenderMale);
		rdbtnGenderMale.setBounds(114, 109, 103, 21);
		contentPane.add(rdbtnGenderMale);
		
		JRadioButton rdbtnGenderFemale = new JRadioButton("Female");
		buttonGroup.add(rdbtnGenderFemale);
		rdbtnGenderFemale.setBounds(213, 109, 59, 21);
		contentPane.add(rdbtnGenderFemale);
		
		JCheckBox chckbxJava = new JCheckBox("Java");
		chckbxJava.setBounds(114, 150, 93, 21);
		contentPane.add(chckbxJava);
		
		JCheckBox chckbxPython = new JCheckBox("Python");
		chckbxPython.setBounds(213, 147, 62, 21);
		contentPane.add(chckbxPython);
		
		JComboBox comboBoxSubject = new JComboBox();
		comboBoxSubject.setModel(new DefaultComboBoxModel(new String[] {"Computer Science", "Management", "Humanty", "Art", "Education"}));
		comboBoxSubject.setBounds(114, 193, 160, 21);
		contentPane.add(comboBoxSubject);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Personel bilgilerini al
                String name = textName.getText();
                String address = textAdress.getText();
                String gender="",knowledge="";
                if(rdbtnGenderFemale.isSelected())gender="female";
                else if(rdbtnGenderMale.isSelected())gender="male";
                if(chckbxJava.isSelected())knowledge+="java";
                else if(chckbxPython.isSelected())knowledge+="python";
                 String subject =comboBoxSubject.getSelectedItem().toString();
                 
                 Personal personel = new Personal(0, name, address, gender, knowledge, subject);

                // PersonelDAO kullanarak veritabanına kaydet
               PersonelDAO dao = new PersonelDAO();
                dao.savePersonel(personel);
                JOptionPane.showMessageDialog(null, "Personel başarıyla eklendi!");
                loadDataToTable();
             //   dispose();				
			}
		});
		btnSave.setBounds(167, 224, 134, 21);
		contentPane.add(btnSave);
		
		JButton btnNewButton = new JButton("Reset");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textName.setText("");
				textAdress.setText("");
				buttonGroup.clearSelection();
				comboBoxSubject.setSelectedIndex(0);
				chckbxJava.setSelected(false);
				chckbxPython.setSelected(false);
			}
		});
		btnNewButton.setBounds(46, 224, 111, 21);
		contentPane.add(btnNewButton);
		
		table_1 = new JTable();
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i= table_1.getSelectedRow();
				TableModel model=table_1.getModel(); 
				selectedId=Integer.parseInt( model.getValueAt(i, 0).toString());
				textName.setText(model.getValueAt(i, 1).toString());
				textAdress.setText(model.getValueAt(i, 2).toString());
				String gender=model.getValueAt(i, 3).toString();
				if(gender.equals("male"))rdbtnGenderMale.setSelected(true);
				else if(gender.equals("female"))rdbtnGenderFemale.setSelected(true);
				String knownledge=model.getValueAt(i, 4).toString();
				if(knownledge.equals("Java")) {chckbxJava.setSelected(true);chckbxPython.setSelected(false);}
				else if(knownledge.equals("Python")) {chckbxPython.setSelected(true);chckbxJava.setSelected(false);}
				else {chckbxPython.setSelected(true);chckbxJava.setSelected(false);}
				String subject=model.getValueAt(i, 5).toString();
				comboBoxSubject.setSelectedItem(subject);
			}
		});
		table_1.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] {"İd", "Name", "Address", "Gender", "Knowledge", "Subject"	}
		));
		table_1.setBounds(284, 35, 498, 181);
		contentPane.add(table_1);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PersonelDAO dao=new PersonelDAO();
				 String name = textName.getText();
	                String address = textAdress.getText();
	                String gender="",knowledge="";
	                if(rdbtnGenderFemale.isSelected())gender="female";
	                else if(rdbtnGenderMale.isSelected())gender="male";
	                if(chckbxJava.isSelected())knowledge+="java";
	                else if(chckbxPython.isSelected())knowledge+="python";
	                 String subject =comboBoxSubject.getSelectedItem().toString();
	                 
	                 Personal personel = new Personal(selectedId, name, address, gender, knowledge, subject);
				dao.updatePersonel(personel);
				loadDataToTable();
			}
		});
		btnUpdate.setBounds(311, 224, 130, 21);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PersonelDAO dao=new PersonelDAO();
				dao.deleteById(selectedId);
				loadDataToTable();
			}
		});
		btnDelete.setBounds(451, 224, 160, 21);
		contentPane.add(btnDelete);
		tableModel=(DefaultTableModel)table_1.getModel();
		loadDataToTable();
      setVisible(true);        
	}
	private void loadDataToTable() {
        PersonelDAO personelDAO = new PersonelDAO();
        ArrayList<Personal> personelList = personelDAO.selectPersonelDatas();
        tableModel.setRowCount(0);
        // Tabloya verileri ekle
        for (Personal personel : personelList) {
            tableModel.addRow(new Object[]{
                personel.getId(),
                personel.getName(),
                personel.getAddress(),
                personel.getGender(),
                personel.getKnowledge(),
                personel.getSubject()
            });
        }
    }
}
