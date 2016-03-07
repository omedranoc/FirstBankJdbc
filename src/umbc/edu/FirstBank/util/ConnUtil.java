package net.mv.FirstBank.util;

	

	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.SQLException;

	public class ConnUtil {
		private static ConnUtil util;
		
		private ConnUtil() throws ClassNotFoundException{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}

		public static ConnUtil getUtil(){
			if (util == null){
				try {
					util = new ConnUtil();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return util;
			
		}
		
		public Connection createConnection(){
			Connection conn = null;
			try {
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","admin","admin");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return conn;
		}
	}



