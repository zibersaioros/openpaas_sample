package openpaas.sample.common.domain.mysql;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="ORG_TBL")
public class MysqlOrg {
	
	@Id @GeneratedValue
	private String id;
	 
	@Column(nullable=false)
	@NotNull
	private String label;
	
	@Column(name="`desc`")
	private String desc;
	
	private String url;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false, updatable=false )
	private Date created;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date modified;
	
		
	@PrePersist
	void prePresist(){
		created = new Date();
	}
	
	@PreUpdate
	void preUpdate(){
		modified = new Date();
	}

	public String getId() {
		return id;
	}


	public void setId(String id) {
		if(id != null && !id.equals(""))
			this.id = id;
	}


	public String getLabel() {
		return label;
	}


	public void setLabel(String label) {
		this.label = label;
	}


	public String getDesc() {
		return desc;
	}


	public void setDesc(String desc) {
		this.desc = desc;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public Date getCreated() {
		return created;
	}


	public void setCreated(Date created) {
		if(created != null)
			this.created = created;
	}


	public Date getModified() {
		return modified;
	}


	public void setModified(Date modified) {
		if(modified != null)
			this.modified = modified;
	}

}