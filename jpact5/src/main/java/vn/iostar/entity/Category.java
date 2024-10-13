package vn.iostar.entity;
import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name="categories")
@NamedQuery(name="Category.findAll", query="SELECT c FROM Category c")
public class Category implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CategoryId")
    private int categoryId;
	
	@Column(name="Categoryname",columnDefinition="nvarchar(500) NULL")
	private String categoryname;
	
	 @Column(name = "Images")
	 private String images;
	
	@Column(name="Status")
	private int status;
	
	
	
	//Declare relationship
	@ToString.Exclude
	@OneToMany(mappedBy="category")
	private List<Video> videos;


	

	public Video addVideo(Video video) {
		getVideos().add(video); 
		video.setCategory(this);
		return video;
	}

	public Video remove(Video video) {
		getVideos().remove(video);
		video.setCategory(this);
		return video;
	}
	

}
