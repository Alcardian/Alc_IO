package se.alcardian.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

public class Alc_IO {
	public static double VERSION=1.1;
	
	/**
	 * 
	 * @param address Web address to file.
	 * @param localFileName Save Location and filename e.g. Drive:\File_Path\Filename.filetype
	 * @throws IOException 
	 */
	public static void download(String address, String localFileName)
			throws IOException {
		OutputStream out = null;
		URLConnection conn = null;
		InputStream in = null;

		URL url = new URL(address);
		out = new BufferedOutputStream(new FileOutputStream(localFileName));
		conn = url.openConnection();
		
		// User-Agent prevents some situations when certain sites return HTTP response code 403 
		// 403 = Forbidden, The server understood the request but refuses to authorize it
		conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
		
		in = conn.getInputStream();
		byte[] buffer = new byte[1024];

		int numRead;
		
		while ((numRead = in.read(buffer)) != -1) {
			out.write(buffer, 0, numRead);
		}
		if (in != null) {
			in.close();
		}
		if (out != null) {
			out.close();
		}
	}
	
	/**
	 * A non-web based function. It returns the file's name based on the address or URL itself
	 * @param address
	 * @return
	 */
	public static String getFileName(String address){
		return address.substring(address.lastIndexOf('/')+1);
	}
	
	/**
	 * A non-web based function. It returns the file's type based on the address or URL itself
	 * @param address
	 * @return
	 */
	public static String getFileType(String address){
		return address.substring(address.lastIndexOf('.'));
	}
	
	/**
	 * Reads a file. Built with the intent of reading simple text files.
	 * @param fileName
	 * @return
	 */
	public static ArrayList<String> ReadFile(String fileName){
		ArrayList<String> buffer = new ArrayList<String>();
		Scanner sc;	//scanner, for read from file
		
		try {
			sc = new Scanner(new File(fileName));
			while (sc.hasNext()){
				buffer.add(sc.nextLine());
			}
			sc.close();
		} catch(FileNotFoundException e){
			e.printStackTrace();
			System.out.println("ERROR! Can't find file!");
		}
		return buffer;
	}
	
	/**
	 * Returns the HTML code of a website as an ArrayList<String>. This method assumes that the site is using UTF-8 as encoding
	 * @param stringURL URL address to site. The address MOST begin with "http://" like "http://google.com" or it will crash.
	 * @return
	 */
	public static ArrayList<String> getWebPage(String stringURL) {
		return getWebPage(stringURL, "UTF-8");
	}
	
	/**
	 * Returns the HTML code of a website as an ArrayList<String>.
	 * @param stringURL URL address to site. The address MOST begin with "http://" like "http://google.com" or it will crash.
	 * @param encoding encoding format of site, example "UTF-8"
	 * @return
	 */
	public static ArrayList<String> getWebPage(String stringURL, String encoding){
		URL url;
		InputStream is = null;
		BufferedReader br;
		String line;
		ArrayList<String> buffer = new ArrayList<String>();
		
		try {
			url = new URL(stringURL);
			is = url.openStream(); // throws an IOException
			br = new BufferedReader(new InputStreamReader(is, encoding));
			
			while ((line = br.readLine()) != null) { // While it can still read more lines
				buffer.add(line); // Save line to buffer
			}
		} catch (MalformedURLException mue) {
			System.out.println(stringURL);	
			mue.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				if (is != null)
					is.close();
			} catch (IOException ioe) {
				// nothing to see here
			}
		}
		
		return buffer;
	}
	
	/**
	 * @param folderPath
	 * @return True if the folder exists, False if it doesn't.
	 */
	public static boolean folderExists(String folderPath){
		return new File(folderPath).isDirectory();
	}
	
	/**
	 * @param filePath e.g. on Windows 10 "C:\Media\"
	 * @param fileName e.g. on Windows 10 "funnyImage.jpg"
	 * @return True if the file exists, False if it doesn't.
	 */
	public static boolean fileExists(String filePath, String fileName){
		return new File(filePath+fileName).isFile();
	}
	
	/**
	 * @param folderPath
	 * @return True if it managed to create folder/s.
	 */
	public static boolean createFolder(String folderPath){
		return new File(folderPath).mkdirs();
	}
}
