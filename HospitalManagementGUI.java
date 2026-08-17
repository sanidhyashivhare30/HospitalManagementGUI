import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class HospitalManagementGUI extends JFrame {
    private final DefaultTableModel patients =
        new DefaultTableModel(new String[]{"ID","Patient","Age","Gender","Disease"},0);
    private final DefaultTableModel doctors =
        new DefaultTableModel(new String[]{"ID","Doctor","Specialization"},0);

    public HospitalManagementGUI() {
        setTitle("Hospital Management System");
        setSize(800,520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel title=new JLabel("HOSPITAL MANAGEMENT SYSTEM",SwingConstants.CENTER);
        title.setFont(new Font("Arial",Font.BOLD,24));
        add(title,BorderLayout.NORTH);

        JTabbedPane tabs=new JTabbedPane();
        tabs.addTab("Dashboard",dashboard());
        tabs.addTab("Patients",patientPanel());
        tabs.addTab("Doctors",doctorPanel());
        tabs.addTab("Appointments",appointmentPanel());
        add(tabs);
    }

    private JPanel dashboard() {
        JPanel p=new JPanel(new GridLayout(2,2,15,15));
        p.setBorder(BorderFactory.createEmptyBorder(40,60,40,60));
        p.add(card("Patients")); p.add(card("Doctors"));
        p.add(card("Appointments")); p.add(card("System Status"));
        return p;
    }
    private JPanel card(String text) {
        JPanel p=new JPanel(new GridLayout(2,1));
        p.setBorder(BorderFactory.createTitledBorder(text));
        p.add(new JLabel(text,SwingConstants.CENTER));
        p.add(new JLabel("Ready",SwingConstants.CENTER));
        return p;
    }
    private JPanel patientPanel() {
        JPanel p=new JPanel(new BorderLayout());
        JTable table=new JTable(patients);
        p.add(new JScrollPane(table));
        JButton add=new JButton("Add Patient");
        add.addActionListener(e->addPatient());
        p.add(add,BorderLayout.SOUTH);
        return p;
    }
    private JPanel doctorPanel() {
        JPanel p=new JPanel(new BorderLayout());
        JTable table=new JTable(doctors);
        p.add(new JScrollPane(table));
        JButton add=new JButton("Add Doctor");
        add.addActionListener(e->addDoctor());
        p.add(add,BorderLayout.SOUTH);
        return p;
    }
    private JPanel appointmentPanel() {
        JPanel p=new JPanel(new GridBagLayout());
        p.add(new JLabel("Appointment scheduling module"));
        return p;
    }
    private void addPatient() {
        JTextField id=new JTextField(),name=new JTextField(),age=new JTextField(),
                   gender=new JTextField(),disease=new JTextField();
        Object[] fields={"Patient ID:",id,"Name:",name,"Age:",age,"Gender:",gender,"Disease:",disease};
        if(JOptionPane.showConfirmDialog(this,fields,"Add Patient",
                JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION) {
            try {
                int a=Integer.parseInt(age.getText().trim());
                if(a<=0) throw new NumberFormatException();
                patients.addRow(new Object[]{id.getText(),name.getText(),a,gender.getText(),disease.getText()});
            } catch(NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,"Enter a valid positive age.");
            }
        }
    }
    private void addDoctor() {
        JTextField id=new JTextField(),name=new JTextField(),spec=new JTextField();
        Object[] fields={"Doctor ID:",id,"Doctor Name:",name,"Specialization:",spec};
        if(JOptionPane.showConfirmDialog(this,fields,"Add Doctor",
                JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION) {
            if(name.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this,"Doctor name is required."); return;
            }
            doctors.addRow(new Object[]{id.getText(),name.getText(),spec.getText()});
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->new HospitalManagementGUI().setVisible(true));
    }
}