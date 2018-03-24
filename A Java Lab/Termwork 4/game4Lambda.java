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
class DbConnect1 {
    private Connection con;
    private Statement st;
    private ResultSet rs;
    
    public DbConnect1(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/game","root","");
            st = con.createStatement();
            
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Error"+e);
        }
    }
    public void getData(){
        try{
            String query = "select * from game_score" ; 
            rs = st.executeQuery(query);
            System.out.println("Records of database: Score details of all games");
            
            while(rs.next()) {
                int game_score = rs.getInt("score");
                //String name = rs.getString("name");
                //String dept = rs.getString("dept");
                
                System.out.println("GAME SCORE: "+game_score);
            }
        }catch(SQLException e){
        
        }
    }
    public void insert_data(int score2){
        try{
            String query1 = "insert into game_score(score)" + "values(?)";
            PreparedStatement ps = con.prepareStatement(query1);
            ps.setInt(1,score2);
            ps.execute();
        }catch(SQLException e){
            
        }
        
    } 
}
class game5 extends DbConnect1 implements ActionListener {
    JFrame jf;
    JLabel jl1, jl2, jl3;
    JButton start,click,stop;
    String msg = "";
    String str;
    int score1,score2;
    game5() {
        jl1 = new JLabel("Press start to start game");
        jf = new JFrame("A Simple Game ");
        jf.setLayout(new FlowLayout());
        jf.setSize(500,100);  
    
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
        
        start = new JButton("Start");
        click = new JButton("Click");
        stop = new JButton("Stop");
        
        jf.add(jl1);
        jf.add(start);
        
        start.addActionListener((ActionListener) this);
        click.addActionListener((ActionListener) this);
        stop.addActionListener((ActionListener) this);
        
    }
    @Override
    public void actionPerformed(ActionEvent ae){
        str = ae.getActionCommand();
        
        if(str.equals("Start")){
            start.setVisible(false);
            jl1.setVisible(false);
            jl2 = new JLabel("Press click to increase score");
            jl3 = new JLabel("Press stop to stop the game");
            jf.add(jl2);
            jf.add(click);
            jf.add(jl3);
            jf.add(stop);
        }
        
        if(str.equals("Click")){
            score1++;
            
            //System.out.println(score1);
        }
        if(str.equals("Stop")){
            int score = score1;
            DbConnect1 connect = new DbConnect1(); 
            connect.insert_data(score);
            connect.getData();
            System.exit(0);
        }
    }
}    

public class game4Lambda extends game5{
    public static void main(String args []){
        SwingUtilities.invokeLater(() -> {
            game5 g = new game5();
            //System.out.println(g.score2);
        });
    }       
}    
