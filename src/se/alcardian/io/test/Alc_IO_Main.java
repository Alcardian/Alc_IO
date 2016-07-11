package se.alcardian.io.test;

import java.io.IOException;
import java.util.ArrayList;

import se.alcardian.io.Alc_IO;

public class Alc_IO_Main {

	/**
	 * @param args To test to view the html code of a site, send its URL as argument (e.g. "https://www.google.se/").
	 * To download an image from the internet, send its URL along with the location on the computer you want to save it to (e.g. "http://i.imgur.com/oo9wB35.jpg" "C:\downloads\test.jpg").
	 * Note that the download method requires the URL to end in the file format (".jpg", ".webm" etc) or it will not work.
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
