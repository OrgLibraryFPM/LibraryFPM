package com.lib.fpm.importdata;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

public class ImportDataTest {
	
	@Test
	public void test() throws IOException, ClassNotFoundException, SQLException{
		ImportData data = new ImportData();
		byte[] dataBase = IOUtils.toByteArray(this.getClass().getResourceAsStream("/importFile/Database6.accdb"));
		data.importData(dataBase);
	}

}
