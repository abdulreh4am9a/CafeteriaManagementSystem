package cms;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.*;


public class dbconnection {
	public static Connection conn() throws SQLException {
		Connection con;
		String dbdir = "C:/Database/";
		File f = new File(dbdir);
		if(!f.exists()) {
			f.mkdir();
		}
		String dbName = "cafeteriamanagementsystem.accdb";
		String url = dbdir+"/"+dbName;
		File f1 = new File(url);
		if(!f1.exists()) {
			InputStream iStream = dbconnection.class.getResourceAsStream("Database/"+dbName);
			try {
				Files.copy(iStream, f1.toPath(), StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		con=DriverManager.getConnection("jdbc:ucanaccess://"+ url); 
		return con;
	}
}