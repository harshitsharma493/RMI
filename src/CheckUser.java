import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;

public class CheckUser extends UnicastRemoteObject implements LoginInterface
{
    public CheckUser()throws RemoteException
    {
        
    }
    public boolean check(String userId,String password )throws RemoteException
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");
            PreparedStatement pt=con.prepareStatement("select * from userlogintable where userId=? and password=?");
            pt.setString(1,userId);
            pt.setString(2,password);
            
            ResultSet rs=pt.executeQuery();
            if(rs.next())
                return true;
            else
                return false;
            
        }catch(Exception ex){
            ex.printStackTrace();;
            return false;}
    }
}
