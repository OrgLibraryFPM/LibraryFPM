package com.lib.fpm.importdata;

import java.io.IOException;
import java.sql.SQLException;

import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.junit.Ignore;
import org.junit.Test;

import com.lib.fpm.importdata.ImportData;
import com.lib.fpm.service.PersistenceTest;

public class ImportDataTest extends PersistenceTest {
	
	@Inject
	private ImportData importData;
	
	@Ignore
	@Test
	public void testImport() throws IOException, ClassNotFoundException, SQLException{
		byte[] dataBase = IOUtils.toByteArray(this.getClass().getResourceAsStream("/importFile/Database6.accdb"));
		importData.importData(dataBase);
		System.out.println();
	}

}
