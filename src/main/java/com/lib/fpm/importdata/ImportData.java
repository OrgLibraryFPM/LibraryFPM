package com.lib.fpm.importdata;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.commons.io.FileUtils;

public class ImportData {
	
	private static final String TABLE_TYPE = "Тип";
	
	public void importData(byte[] data) throws IOException, ClassNotFoundException, SQLException{
		File dataBase = File.createTempFile("dataBaseLIB", "accdb");
		Connection con = null;
		try{
			FileUtils.writeByteArrayToFile(dataBase, data);
			String path = dataBase.getAbsolutePath();
		    String db ="JDBC:ODBC:Driver=Microsoft Access Driver (*.mdb, *.accdb); DBQ="+path;
		    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		    Properties conInfo = new Properties();
	        conInfo.put("user", "");
	        conInfo.put("password","");
	        conInfo.put("charSet", "Cp1251");
            con = DriverManager.getConnection(db, conInfo);
            Statement st = con.createStatement();
            String string = "select * from " + TABLE_TYPE;
			ResultSet rs = st.executeQuery(string);
            while(rs.next()){
            	System.out.println(rs.getObject(1));
                System.out.println(rs.getObject(2));
            }
		} finally{
			if (con!=null){
				con.close();
			}
			dataBase.deleteOnExit();
		}
	}
}
