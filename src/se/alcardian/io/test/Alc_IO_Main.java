package se.alcardian.io.test;

import java.io.IOException;
import java.util.ArrayList;

import se.alcardian.io.Alc_IO;

public class Alc_IO_Main {

	/**
	 * @param args 
	 */
	public static void main(String[] args) {
		if(args.length == 1){
			testGetWeb(args[0]);
		}else if(args.length == 2){
			testDownload(args[0], args[1]);
		}
		
	}

	public static void testDownload(String address, String localFileName) {
		try {
			Alc_IO.download(address, localFileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Filetype: " + Alc_IO.getFileType(address));
		System.out.println("Filename on site with Filetype: "+ Alc_IO.getFileName(address));
	}
	public static void testGetWeb(String address){
		ArrayList<String> buffer = Alc_IO.getWebPage(address);
		
		for(int a=0; a<buffer.size(); a++){
			System.out.println(buffer.get(a));
		}
	}
}
