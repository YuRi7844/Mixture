package com.buskstop.vo;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class Artist implements Serializable {
	
	private String artistId;
	private String artistName;
	private String performance;
	private String profile;
	private String artistImage;
	private MultipartFile multiImage;
	private String artistMembers;
	private String artistSns;
	
	//
	private int followCount;
	
	public Artist() {
	}
	
	public Artist(String artistId, int followCount) {
		this.artistId = artistId;
		this.followCount = followCount;
	}

	public Artist(String artistName, String performance, String profile, MultipartFile multiImage, String artistMembers,
			String artistSns) {
		this.artistName = artistName;
		this.performance = performance;
		this.profile = profile;
		this.multiImage = multiImage;
		this.artistMembers = artistMembers;
		this.artistSns = artistSns;
	}



	public Artist(String artistId, String artistName, String performance, String profile, String artistImage,
			MultipartFile multiImage, String artistMembers, String artistSns) {
		this.artistId = artistId;
		this.artistName = artistName;
		this.performance = performance;
		this.profile = profile;
		this.artistImage = artistImage;
		this.multiImage = multiImage;
		this.artistMembers = artistMembers;
		this.artistSns = artistSns;
	}
	
	
	
	public int getFollowCount() {
		return followCount;
	}

	public void setFollowCount(int followCount) {
		this.followCount = followCount;
	}

	public String getArtistId() {
		return artistId;
	}

	public void setArtistId(String artistId) {
		this.artistId = artistId;
	}

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}

	public String getPerformance() {
		return performance;
	}

	public void setPerformance(String performance) {
		this.performance = performance;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getArtistImage() {
		return artistImage;
	}

	public void setArtistImage(String artistImage) {
		this.artistImage = artistImage;
	}

	public MultipartFile getMultiImage() {
		return multiImage;
	}

	public void setMultiImage(MultipartFile multiImage) {
		this.multiImage = multiImage;
	}

	public String getArtistMembers() {
		return artistMembers;
	}

	public void setArtistMembers(String artistMembers) {
		this.artistMembers = artistMembers;
	}

	public String getArtistSns() {
		return artistSns;
	}

	public void setArtistSns(String artistSns) {
		this.artistSns = artistSns;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((artistId == null) ? 0 : artistId.hashCode());
		result = prime * result + ((artistImage == null) ? 0 : artistImage.hashCode());
		result = prime * result + ((artistMembers == null) ? 0 : artistMembers.hashCode());
		result = prime * result + ((artistName == null) ? 0 : artistName.hashCode());
		result = prime * result + ((artistSns == null) ? 0 : artistSns.hashCode());
		result = prime * result + ((multiImage == null) ? 0 : multiImage.hashCode());
		result = prime * result + ((performance == null) ? 0 : performance.hashCode());
		result = prime * result + ((profile == null) ? 0 : profile.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Artist other = (Artist) obj;
		if (artistId == null) {
			if (other.artistId != null)
				return false;
		} else if (!artistId.equals(other.artistId))
			return false;
		if (artistImage == null) {
			if (other.artistImage != null)
				return false;
		} else if (!artistImage.equals(other.artistImage))
			return false;
		if (artistMembers == null) {
			if (other.artistMembers != null)
				return false;
		} else if (!artistMembers.equals(other.artistMembers))
			return false;
		if (artistName == null) {
			if (other.artistName != null)
				return false;
		} else if (!artistName.equals(other.artistName))
			return false;
		if (artistSns == null) {
			if (other.artistSns != null)
				return false;
		} else if (!artistSns.equals(other.artistSns))
			return false;
		if (multiImage == null) {
			if (other.multiImage != null)
				return false;
		} else if (!multiImage.equals(other.multiImage))
			return false;
		if (performance == null) {
			if (other.performance != null)
				return false;
		} else if (!performance.equals(other.performance))
			return false;
		if (profile == null) {
			if (other.profile != null)
				return false;
		} else if (!profile.equals(other.profile))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Artist [artistId=" + artistId + ", artistName=" + artistName + ", performance=" + performance
				+ ", profile=" + profile + ", artistImage=" + artistImage + ", multiImage=" + multiImage
				+ ", artistMembers=" + artistMembers + ", artistSns=" + artistSns + ", followCount=" + followCount
				+ "]";
	}

	
}
