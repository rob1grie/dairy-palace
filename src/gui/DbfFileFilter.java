package gui;

import java.io.File;
import java.io.FileFilter;
import utils.Utils;

public class DbfFileFilter implements FileFilter {

	@Override
	public boolean accept(File f) {
		if(f.isDirectory()) {
			return false;
		}
		String name = f.getName();

		String extension = Utils.getFileExtension(name);

		if (extension == null) {
			return false;
		}
		
		if(extension.toLowerCase().equals("dbf")) {
			return true;
		}
		
		return false;
	}

}
