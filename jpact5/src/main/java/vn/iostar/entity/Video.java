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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name="videos")
@NamedQuery(name="Video.findAll", query="SELECT v FROM Video v")
public class Video {

	@Id
	@Column(name="VideoId")
	private String videoId;
	
	@Column(name="Active")
	private String active;
	
	@Column(name="Images")
	private String images;
	
	@Column(name="Description",columnDefinition = "nvarchar(max) NULL")
	private String description;
	
	@Column(name="Title")
	private String title;
	
	@Column(name="Views")
	private String views;
	
	//Declare relationship
	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name="CategoryId")
	private Category category;

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	
	
}
