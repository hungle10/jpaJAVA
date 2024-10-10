package vn.iostar.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="videos")
@NamedQuery(name="Video.findAll", query="SELECT v FROM Video v")
public class Video {

	@Id
	@Column(name="VideoId")
	private String videoId;
	
	@Column(name="Active")
	private String active;
	
	@Column(name="Description",columnDefinition = "nvarchar(max) NULL")
	private String description;
	
	@Column(name="Title")
	private String title;
	
	@Column(name="Views")
	private String views;
	
	//Declare relationship
	@ManyToOne
	@JoinColumn(name="CategoryId")
	private Category category;

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	public Video() {
	}
	
	
}
