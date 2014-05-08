package com.lib.fpm.importdata;

import java.io.IOException;
import java.sql.SQLException;

public interface ImportData {
	
	public void importData(byte[] data) throws IOException, ClassNotFoundException, SQLException;
}
