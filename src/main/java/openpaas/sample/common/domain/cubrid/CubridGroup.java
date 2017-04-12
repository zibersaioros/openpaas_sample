package openpaas.sample.common.domain.cubrid;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="GROUP_TBL")
public class CubridGroup {
	
	@Id @GeneratedValue
	private String id;
	
	@ManyToOne @JoinColumn(nullable=false, name="org_id", referencedColumnName="id") 
	private CubridOrg org;
	
	@ManyToOne @JoinColumn( name="parent_id", referencedColumnName="id")
	private CubridGroup parent;
	
	@Column(nullable=false)
	private String label;
	
	@Column(name="`desc`")
	private String desc;
	
	private String thumb_img_name;
	
	private String thumb_img_path;
	
	private String url;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false, updatable=false)
	private Date created;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date modified;
	
	@Transient
	private String superior;
	
	@PrePersist
	public void prePersist(){
		created = new Date();
	}
	
	@PreUpdate
	public void preUpdate(){
		modified = new Date();
	}
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public CubridOrg getOrg() {
		return org;
	}

	public void setOrg(CubridOrg org) {
//		if(this.org != null)
//			this.org.getGroups().remove(this);
		this.org = org;
//		if(org != null)
//			org.getGroups().add(this);
	}

	public CubridGroup getParent() {
		return parent;
	}

	public void setParent(CubridGroup parent) {
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

