package com.prodyna.pac.vothing.persistence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.Gson;
import com.prodyna.pac.vothing.VothingConstants;

@Entity
@XmlRootElement
@Table(name = "vothing_server")
@NamedQueries({ @NamedQuery(name = VothingConstants.SELECT_ALL_SERVER, query = "SELECT a FROM Server a") })
public class Server implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4932957284138825267L;

	@Id
	@GeneratedValue
	private long serverId;

	@Column
	private String privateKey;

	@Column
	private String schemaVersion;
	
	public long getServerId() {
		return serverId;
	}

	public void setServerId(long serverId) {
		this.serverId = serverId;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	public String getSchemaVersion() {
		return schemaVersion;
	}

	public void setSchemaVersion(String schemaVersion) {
		this.schemaVersion = schemaVersion;
	}

	@Override
	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
}
