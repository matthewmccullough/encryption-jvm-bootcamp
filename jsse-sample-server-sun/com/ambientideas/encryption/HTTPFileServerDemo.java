package com.ambientideas.encryption;

public class HTTPFileServerDemo {
	public static void main(String args[])
	{
		int serverSocketFactoryPort = -1;
		String serverSocketFactoryType = null;
		String docroot = null;
		boolean needClientAuth = false;

		if (args.length < 4) {
			explainUsageParameters();
		}
		
		//Parse CLI parameters
		serverSocketFactoryPort = Integer.parseInt(args[0]);
		docroot = args[1];
		serverSocketFactoryType = args[2];
		needClientAuth = Boolean.parseBoolean(args[3]);
		
		HTTPFileServer.startSocketServer(serverSocketFactoryPort, serverSocketFactoryType,
				docroot, needClientAuth);
	}

	private static void explainUsageParameters() {
		//Explain usage parameters
		System.out.println("USAGE: java HTTPFileServer port docroot clientauth");
		System.out.println("");
		System.out.println(
				"* port = the port to run the server on\n" +
				"* docroot = the directory of documents to serve\n" +
				"* sockettype = PlainSocket|SSL|TLS\n" +
				"* clientauth = Force client to authenticate with a certificate\n");
	}
}
