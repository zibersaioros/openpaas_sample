package openpaas.sample.common.domain.mongo;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Groups")
public class MongoGroup {
	@Id 
	private String id;
	
	@DBRef
	private MongoOrg org;
	
	@DBRef
	private MongoGroup parent;
	
	private String label;
	private String desc;
	
	private String thumb_img_name;
	
	private String thumb_img_path;
	
	private String url;
	
	private Date created;
	
	private Date modified;
	
	@org.springframework.data.annotation.Transient
	private String superior;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public MongoOrg getOrg() {
		return org;
	}

	public void setOrg(MongoOrg org) {
//		if(this.org != null)
//			this.org.getGroups().remove(this);
		this.org = org;
//		if(org != null)
//			org.getGroups().add(this);
	}

	public MongoGroup getParent() {
		return parent;
	}

	public void setParent(MongoGroup parent) {
//		if(this.parent != null)
//			this.parent.getChildren().remove(this);
		this.parent = parent;
//		if(parent != null)
//			parent.getChildren().add(this);
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

	public String getThumb_img_name() {
		return thumb_img_name;
	}

	public void setThumb_img_name(String thumb_img_name) {
		this.thumb_img_name = thumb_img_name;
	}

	public String getThumb_img_path() {
		return thumb_img_path;
	}

	public void setThumb_img_path(String thumb_img_path) {
		this.thumb_img_path = thumb_img_path;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSuperior() {
		return superior;
	}

	public void setSuperior(String superior) {
		this.superior = superior;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}
}
