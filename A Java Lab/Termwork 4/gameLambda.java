/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kishan
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kishan
 */
class DbConnect3 {
    private Connection con;
    private Statement st;
    private ResultSet rs;
    
    public DbConnect3() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/game1";
            con = DriverManager.getConnection(url,"root","");
            st = con.createStatement();
        }catch(ClassNotFoundException | SQLException e) {
            System.out.println("Error"+e);
        }
    }
    public void getData() {
        try{
            String query = "select * from game_score" ; 
            rs = st.executeQuery(query);
            System.out.println("Records of database");
            
            while(rs.next()) {
                String uname = rs.getString("username");
                int game_score = rs.getInt("score");
                //String name = rs.getString("name");
                //String dept = rs.getString("dept");
                
                System.out.println("Username: " +uname+" GAME SCORE: " +game_score);
            }
        }catch(SQLException e) {
            System.out.println("Exception "+e);
        }
    }
    public void insert_data(String uname ,int score2) {
        try{
            String query1 = "insert into game_score(username,score)" + "values(?,?)";
            PreparedStatement ps = con.prepareStatement(query1);
            ps.setString(1,uname);
            ps.setInt(2, score2);
            
            ps.execute();
        }catch(SQLException e) {
            System.out.println("Exception "+e);
        }
        
    } 
}
class game7 extends DbConnect3 implements ActionListener {
    JFrame jf;
    JLabel jl, jl1, jl2, jl3;
    JButton start,click,stop;
    String msg = "";
    String str;
    String str2;
    int score1,score2;
    JTextField jtf;
    game7() {
        jl =new JLabel("Enter username");
        jl1 = new JLabel("Press start to start game");
        jf = new JFrame("A Simple Game ");
        jf.setLayout(new FlowLayout());
        jf.setSize(500,100);  
    
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
        
        start = new JButton("Start");
        click = new JButton("Click");
        stop = new JButton("Stop");
        jtf = new JTextField(30);
        
        jf.add(jl);
        jf.add(jtf);
        jf.add(jl1);
        jf.add(start);
        
        start.addActionListener((ActionListener) this);
        click.addActionListener((ActionListener) this);
        stop.addActionListener((ActionListener) this);
        
    }
    //@Override
    public void actionPerformed(ActionEvent ae) {
        str = ae.getActionCommand();
        
        if(str.equals("Start")) {
            str2 = jtf.getText();
            start.setVisible(false);
            jl1.setVisible(false);
            jl.setVisible(false);
            jtf.setVisible(false);
            jl2 = new JLabel("Press click to increase score");
            jl3 = new JLabel("Press stop to stop the game");
            jf.add(jl2);
            jf.add(click);
            jf.add(jl3);
            jf.add(stop);
        }
        
        if(str.equals("Click")) {
            score1++;
            
            //System.out.println(score1);
        }
        if(str.equals("Stop")) {
            int score = score1;
            String uname = str2;
            DbConnect3 connect = new DbConnect3(); 
            connect.insert_data(uname,score);
            connect.getData();
            System.exit(0);
        }
    }
}    

public class gameLambda extends game7 {
    public static void main(String args []){
        SwingUtilities.invokeLater(() -> {
            game7 g = new game7();
            //System.out.println(g.score2);
        });
    }       
}    
