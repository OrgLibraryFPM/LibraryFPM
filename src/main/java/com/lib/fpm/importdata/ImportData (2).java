package com.lib.fpm.importdata;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.io.FileUtils;

public class ImportData {
	
	public void importData(byte[] data) throws IOException, ClassNotFoundException, SQLException{
		File dataBase = File.createTempFile("dataBaseLIB", "accdb");
		Connection con = null;
		try{
			FileUtils.writeByteArrayToFile(dataBase, data);
			String path = dataBase.getAbsolutePath();
		    String db ="JDBC:ODBC:Driver=Microsoft Access Driver (*.mdb, *.accdb); DBQ="+path;
		    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            con = DriverManager.getConnection(db);
		} finally{
			if (con!=null){
				con.close();
			}
			dataBase.deleteOnExit();
		}
	}
}
