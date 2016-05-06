package com.prodyna.pac.vothing.persistence;

import com.google.gson.Gson;
import com.prodyna.pac.vothing.Vothing;

import javax.inject.Inject;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@XmlRootElement
@Inheritance
@MappedSuperclass
public abstract class BaseModelImpl<T> implements BaseModel<T>, Serializable {

    @Inject
    @Transient
    private Vothing vothing;

	@Id
	@GeneratedValue
	private long id;
	
	@Column
	private String name;

	@Column
	private Date createDate;

	@Column
	private Date modifiedDate;

    @Transient
	private List<String> usersPermissions = new ArrayList<String>();

    @Override
    public List<String> getUsersPermissions() {
        return usersPermissions;
    }

    @Override
    public void setUsersPermissions(List<String> usersPermissions) {
        this.usersPermissions = usersPermissions;
    }

    @PostLoad
    private void initBaseModelImpl() {
        // TODO init user in this class context
        // System.out.println(vothing.getUser().getName());
        this.usersPermissions.add("permission1");
        this.usersPermissions.add("permission2");
        this.usersPermissions.add("permission3");
    }

	@Override
	public long getId() {
		return id;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public Date getModifiedDate() {
		return modifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Override
	public Date getCreateDate() {
		return createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}

	@Override
	public int hashCode() {
		int hash = 17;
		hash = hash * 31 + Long.valueOf(this.getId()).intValue();
		hash = hash * 31 + this.getName().hashCode();
		hash = hash * 31 + this.getClass().getName().hashCode();
		return hash;
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj == null) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof BaseModel)) {
			return false;
		}
		final BaseModel other = (BaseModel) obj;
		if(this.getId() == other.getId()) {
			return true;
		}
		return false;
	}
}
