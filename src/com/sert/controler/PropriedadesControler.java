package com.sert.controler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
public class PropriedadesControler {
 
	private String login;
    private String port;
    private String host;
    private String password;
     
    private Properties prop;
	
    public PropriedadesControler() throws IOException {
    	prop = getProp();
        login = prop.getProperty("prop.server.login");
        host = prop.getProperty("prop.server.host");
        port = prop.getProperty("prop.server.port");
        password = prop.getProperty("prop.server.password");
	}
    
    public static Properties getProp() throws IOException {
        Properties props = new Properties();
        FileInputStream file = new FileInputStream("C:/Program Files/Sert+/Config/config.properties");
        props.load(file);
        return props;
 
    }
    
    public void setProp() throws IOException {
    	prop = getProp();
    	FileOutputStream arquivoOut = new FileOutputStream("C:/Program Files/Sert+/Config/config.properties");
    	prop.setProperty("prop.server.login", login);
        prop.setProperty("prop.server.host", host);
        prop.setProperty("prop.server.port", port);
        prop.setProperty("prop.server.password", password);
        prop.store(arquivoOut, null);
    }

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}