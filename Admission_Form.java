package src;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class Admission_Form {
	JFrame f;
	JButton b_smt,b_rst,b3;
	JTextField t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13;
	JRadioButton rb1,rb2;
	JComboBox<String> jcb1,jcb2,jcb3;
	JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14,l15,l16,l17;
	JPasswordField pf;
	ButtonGroup bg; // Declare ButtonGroup as a class-level variable
	public Admission_Form()
	{
		f=new JFrame("Admission  Form");
		l1=new JLabel("Value-Added Course ");
		l1.setBounds(230,10,150,40);
		f.add(l1);
		l2=new JLabel("Student UID");
		l2.setBounds(20,60,60,20);
		f.add(l2);
		l3=new JLabel("Student Name");
		l3.setBounds(20, 90, 80, 20);
		f.add(l3);
		l4=new JLabel("Father Name");
		l4.setBounds(20,120,80,20);
		f.add(l4);
		l5=new JLabel("Address");
		l5.setBounds(20, 150, 80, 20);
		f.add(l5);
		l6=new JLabel("Gender");
		l6.setBounds(20, 180, 80, 20);
		f.add(l6);
		l7=new JLabel("Date of Birth");
		l7.setBounds(20, 210, 80, 20);
		f.add(l7);
		l8=new JLabel("Date of Join");
		l8.setBounds(20, 240, 100, 20);
		f.add(l8);
		l9=new JLabel("Course");
		l9.setBounds(20, 270, 80, 20);
		f.add(l9);
		l10=new JLabel("Phone No");
		l10.setBounds(20, 300, 80, 20);
		f.add(l10);
		l11=new JLabel("Email");
		l11.setBounds(20, 330, 80, 20);
		f.add(l11);
		l12=new JLabel("Nationality");
		l12.setBounds(280, 60, 80, 20);
		f.add(l12);
		l13 = new JLabel("High School Mark");
		l13.setBounds(280, 90, 120, 20);
		f.add(l13);
		l14=new JLabel("Password");
		l14.setBounds(280, 120, 80, 20);
		f.add(l14);
		l16=new JLabel("Emergency contact");
		l16.setBounds(280, 150, 110, 20);
		f.add(l16);
		l17=new JLabel("Blood Group");
		l17.setBounds(280, 180, 110, 20);
		f.add(l17);
		
		b_smt=new JButton("SUBMIT");
		b_smt.setBounds(100, 400, 100, 20); // Adjusted width to fit the text
		Color vs = new Color(173, 216, 230); // Light blue color
		b_smt.setBackground(vs);
		b_smt.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String studentID = t2.getText();
		        String studentName = t3.getText();
		        String fatherName = t4.getText();
		        String address = t5.getText();
		        String dobText = t6.getText();
		        String dojText = t7.getText();
		        java.sql.Date sqlDob = null;
		        java.sql.Date sqlDoj = null;

		        try {
		            if (dobText != null && !dobText.trim().isEmpty() && !dobText.equals("DD/MM/YYYY")) {
		                SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
		                java.util.Date utilDate = inputFormat.parse(dobText);
		                sqlDob = new java.sql.Date(utilDate.getTime());
		            }
		            if (dojText != null && !dojText.trim().isEmpty() && !dojText.equals("DD/MM/YYYY")) {
		                SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
		                java.util.Date utilDate = inputFormat.parse(dojText);
		                sqlDoj = new java.sql.Date(utilDate.getTime());
		            }
		        } catch (ParseException e) {
		            JOptionPane.showMessageDialog(f, "Invalid Date Format. Please use DD/MM/YYYY.", "Input Error", JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        String course = (String) jcb1.getSelectedItem();
		        String phone = t11.getText();
		        String emergencyContact = t13.getText();

		        // Validate phone and emergency contact numbers
		        if (phone.length() != 10 || !phone.matches("\\d{10}")) {
		            JOptionPane.showMessageDialog(f, "Phone number must be exactly 10 digits.", "Input Error", JOptionPane.ERROR_MESSAGE);
		            return;
		        }
		        if (emergencyContact.length() != 10 || !emergencyContact.matches("\\d{10}")) {
		            JOptionPane.showMessageDialog(f, "Emergency contact must be exactly 10 digits.", "Input Error", JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        String fullPhone = jcb2.getSelectedItem() + phone;
		        String email = t8.getText();
		        String nationality = t9.getText();
		        String idMark = t10.getText();
		        String password = new String(pf.getPassword());
		        String bloodGroup = (String) jcb3.getSelectedItem();

		        try {
		            Connection conn = DriverManager.getConnection(
		                "jdbc:mysql://localhost:3306/admission", "root", "root"
		            );
		            String sql = "INSERT INTO students (student_id, student_name, father_name, address, dob, doj, course, phone, email, nationality, id_mark, password, emergency_contact, blood_group) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		            PreparedStatement pstmt = conn.prepareStatement(sql);
		            pstmt.setString(1, studentID);
		            pstmt.setString(2, studentName);
		            pstmt.setString(3, fatherName);
		            pstmt.setString(4, address);
		            if (sqlDob != null) {
		                pstmt.setDate(5, sqlDob);
		            } else {
		                pstmt.setNull(5, java.sql.Types.DATE);
		            }
		            if (sqlDoj != null) {
		                pstmt.setDate(6, sqlDoj);
		            } else {
		                pstmt.setNull(6, java.sql.Types.DATE);
		            }
		            pstmt.setString(7, course);
		            pstmt.setString(8, fullPhone);
		            pstmt.setString(9, email);
		            pstmt.setString(10, nationality);
		            pstmt.setString(11, idMark);
		            pstmt.setString(12, password);
		            pstmt.setString(13, emergencyContact);
		            pstmt.setString(14, bloodGroup);

		            int rowsInserted = pstmt.executeUpdate();
		            if (rowsInserted > 0) {
		                JOptionPane.showMessageDialog(f, "Data inserted successfully!");
		            }

		            pstmt.close();
		            conn.close();
		        } catch (SQLException e) {
		            JOptionPane.showMessageDialog(f, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
		        }
			}
			
		});
		f.add(b_smt);
		b_rst=new JButton("Reset");
		b_rst.setBounds(360, 400, 80, 20);
		Color bs = new Color(255, 182, 193); // Light red color
		b_rst.setBackground(bs);
		b_rst.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t2.setText("");
                t3.setText("");
                t4.setText("");
                t5.setText("");
                t6.setText("DD/MM/YYYY"); // Retain placeholder text
                t7.setText("DD/MM/YYYY"); // Retain placeholder text
                t8.setText("");
                t9.setText("");
                t10.setText("");
                t11.setText("");
                t13.setText("");
                pf.setText("");
                jcb1.setSelectedIndex(0);
                jcb2.setSelectedIndex(0);
                jcb3.setSelectedIndex(0);
                bg.clearSelection(); // Clear the selection of radio buttons
            }
        });
		f.add(b_rst);
		rb1=new JRadioButton("Male");
		rb1.setBounds(100, 180, 60, 20);
		f.add(rb1);
		rb2=new JRadioButton("Female");
		rb2.setBounds(160, 180, 80, 20);
		f.add(rb2);
		String[] courses = {
		    "Select Your Course",
		    "Artificial Intelligence (AI) & Machine Learning",
		    "Data Science & Data Analytics",
		    "Cloud Computing (AWS, Azure, Google Cloud)",
		    "Cybersecurity & Ethical Hacking",
		    "DevOps & Site Reliability Engineering (SRE)",
		    "Full Stack Web Development (MERN/Java/Python)",
		    "Mobile App Development (Flutter, React Native, Kotlin, Swift)",
		    "Blockchain & Web3 Development",
		    "Generative AI & Prompt Engineering",
		    "UI/UX Design & Product Design",
		    "Internet of Things (IoT)",
		    "AR/VR & Metaverse Development",
		    "Big Data & Apache Spark/Hadoop",
		    "Business Intelligence (Power BI, Tableau, Looker)",
		    "Database Management (SQL, NoSQL, MongoDB, PostgreSQL)",
		    "Automation & RPA (UiPath, Automation Anywhere, Blue Prism)",
		    "Quantum Computing (beginner foundations)",
		    "5G & Networking Technologies",
		    "Game Development (Unity, Unreal Engine)",
		    "IT Project Management (Agile, Scrum, PMP)"
		};
		jcb1 = new JComboBox<>(courses);
		jcb1.setBounds(110, 270, 300, 20);
		jcb1.setEditable(true);
		f.add(jcb1);
		String[] cn = {
		    "+91", "+1", "+20", "+27", "+30", "+31", "+32", "+33", "+34", "+39", "+41", "+44", "+49", "+52", "+55", "+60", "+61", "+62", "+63", "+64", "+65", "+66", "+81", "+82", "+86", "+90", "+92", "+94", "+95", "+98", "+212", "+234", "+251", "+254", "+255", "+256", "+260", "+350", "+351", "+352", "+353", "+354", "+355", "+356", "+357", "+358", "+359", "+370", "+371", "+372", "+373", "+374", "+375", "+376", "+377", "+378", "+380", "+381", "+385", "+386", "+387", "+389", "+420", "+421", "+423", "+500", "+501", "+502", "+503", "+504", "+505", "+506", "+507", "+509", "+591", "+592", "+593", "+594", "+595", "+596", "+597", "+598", "+599", "+670", "+672", "+673", "+674", "+675", "+676", "+677", "+678", "+679", "+680", "+681", "+682", "+683", "+685", "+686", "+687", "+688", "+689", "+690", "+691", "+692", "+850", "+852", "+853", "+855", "+856", "+880", "+886", "+960", "+961", "+962", "+963", "+964", "+965", "+966", "+967", "+968", "+970", "+971", "+972", "+973", "+974", "+975", "+976", "+977", "+992", "+993", "+994", "+995", "+996", "+998"
		};
		jcb2 = new JComboBox<>(cn);
		jcb2.setBounds(110, 300, 50, 20);
		jcb2.setEditable(true);
		f.add(jcb2);
		String[] bloodGroups = {
		    "Select Blood Group",
		    "A+",
		    "A−",
		    "B+",
		    "B−",
		    "AB+",
		    "AB−",
		    "O+",
		    "O−"
		};
		jcb3 = new JComboBox<>(bloodGroups);
		jcb3.setBounds(400, 180, 150, 20);
		jcb3.setEditable(false);
		f.add(jcb3);
		bg=new ButtonGroup();
		bg.add(rb1);bg.add(rb2);
		b3=new JButton("Show");
		b3.setBounds(500, 120, 70, 20);
		b3.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				t1.setText( new String(pf.getPassword()));
			}
			
		});
		
		f.add(b3);
		pf=new JPasswordField();
		pf.setBounds(400, 120, 100, 20);
		f.add(pf);
		t1 = new JTextField();
		t1.setBounds(400, 120, 100, 20);
		f.add(t1);
		t2=new JTextField();
		t2.setBounds(110, 60, 150, 20);
		f.add(t2);
		t3=new JTextField();
		t3.setBounds(110, 90, 150, 20);
		f.add(t3);
		t4=new JTextField();
		t4.setBounds(110, 120, 150, 20);
		f.add(t4);
		t5=new JTextField();
		t5.setBounds(110, 150, 150, 20);
		f.add(t5);
		t6=new JTextField("DD/MM/YYYY");
		t6.setBounds(110, 210, 150, 20);
		t6.addFocusListener(new java.awt.event.FocusAdapter() {
		    @Override
		    public void focusGained(java.awt.event.FocusEvent e) {
		        if (t6.getText().equals("DD/MM/YYYY")) {
		            t6.setText("");
		        }
		    }

		    @Override
		    public void focusLost(java.awt.event.FocusEvent e) {
		        if (t6.getText().isEmpty()) {
		            t6.setText("DD/MM/YYYY");
		        }
		    }
		});
		f.add(t6);
		t7=new JTextField("DD/MM/YYYY");
		t7.setBounds(110, 240, 150, 20);
		t7.addFocusListener(new java.awt.event.FocusAdapter() {
		    @Override
		    public void focusGained(java.awt.event.FocusEvent e) {
		        if (t7.getText().equals("DD/MM/YYYY")) {
		            t7.setText("");
		        }
		    }

		    @Override
		    public void focusLost(java.awt.event.FocusEvent e) {
		        if (t7.getText().isEmpty()) {
		            t7.setText("DD/MM/YYYY");
		        }
		    }
		});
		f.add(t7);
		t8=new JTextField();
		t8.setBounds(110, 330, 150, 20);
		f.add(t8);
		t9=new JTextField();
		t9.setBounds(400, 60, 150, 20);
		f.add(t9);
		t10=new JTextField();
		t10.setBounds(400, 90, 150, 20);
		f.add(t10);
		t11=new JTextField();
		t11.setBounds(160, 300, 100, 20);
		f.add(t11);
		t13=new JTextField();
		t13.setBounds(400, 150,150 ,20);
		f.add(t13);
		f.setSize(600, 500);
		f.setLayout(null);
		
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}
	public static void main(String args[])
	{
		new Admission_Form();
	}
}