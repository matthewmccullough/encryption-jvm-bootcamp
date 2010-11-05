package com.ambientideas.encryption;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.security.KeyStore;

import javax.net.ServerSocketFactory;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;

/**
 * HTTPFileServer.java -- a simple file server that can server
 * HTTP GET request in both clear and secure channel
 *
 * The HTTPFileServer implements a HTTPServer that
 * reads files from the file system.
 * 
 * Derived from Sun SSL example.
 */
public class HTTPFileServer extends HTTPServer {

	private String docroot;

	/**
	 * Constructs a HTTPFileServer.
	 *
	 * @param path the path where the server locates files
	 */
	public HTTPFileServer(ServerSocket ss, String docroot) throws IOException
	{
		super(ss);
		this.docroot = docroot;
	}

	/**
	 * Returns an array of bytes containing the bytes for
	 * the file represented by the argument <b>path</b>.
	 *
	 * @return the bytes for the file
	 * @exception FileNotFoundException if the file corresponding
	 * to <b>path</b> could not be loaded.
	 */
	public byte[] getBytesOfFileOnDisk(String path)
	throws IOException
	{
		System.out.println("reading file: " + path);
		File f = new File(docroot + File.separator + path);
		int length = (int)(f.length());
		if (length == 0) {
			throw new IOException("File length is zero: " + path);
		} else {
			FileInputStream fin = new FileInputStream(f);
			DataInputStream in = new DataInputStream(fin);

			byte[] bytecodes = new byte[length];
			in.readFully(bytecodes);
			return bytecodes;
		}
	}

	public static ServerSocketFactory buildServerSocketFactory(String type) {
		//SSL or TLS Encrypted Socket
		if (type.equals("SSL") || type.equals("TLS") ) {
			SSLServerSocketFactory ssf = null;
			try {
				// set up key manager to do server authentication
				SSLContext ctx;
				KeyManagerFactory kmf;
				KeyStore ks;
				char[] passphrase = "passphrase".toCharArray();

				//The simple line that lets us toggle from SSL to TLS
				ctx = SSLContext.getInstance(type);
				kmf = KeyManagerFactory.getInstance("SunX509");
				ks = KeyStore.getInstance("JKS");

				ks.load(new FileInputStream("keys/sample.keystore"), passphrase);
				kmf.init(ks, passphrase);
				ctx.init(kmf.getKeyManagers(), null, null);

				ssf = ctx.getServerSocketFactory();
				return ssf;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			//Encryptionless plain server
			return ServerSocketFactory.getDefault();
		}
		return null;
	}
	
	static void startSocketServer(int serverSocketFactoryPort,
			String serverSocketFactoryType, String docroot,
			boolean needClientAuth) {
		//Create the server socket
		try {
			ServerSocketFactory ssf =
				buildServerSocketFactory(serverSocketFactoryType);
			ServerSocket ss = ssf.createServerSocket(serverSocketFactoryPort);
			if (needClientAuth) {
				((SSLServerSocket)ss).setNeedClientAuth(true);
			}
			new HTTPFileServer(ss, docroot);
			System.out.println("Server started successfully. Listening for requests.");
		} catch (IOException e) {
			System.out.println("Unable to start HTTPServer: " +
					e.getMessage());
			e.printStackTrace();
		}
	}
}
