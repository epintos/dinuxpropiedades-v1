package ar.edu.itba.it.paw.services.beans;

public class Photos {

	private byte[] file;
	private String name;
	
	public Photos(byte[] file, String name) {
	    this.file = file;
	    this.name = name;
	}
	
	public byte[] getFile() {
	    return file;
	}
	
	public String getName() {
	    return name;
	}
	
	
}
