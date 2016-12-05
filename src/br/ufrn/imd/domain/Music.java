package br.ufrn.imd.domain;

/**
 * 
 * Class that represents a music
 * 
 * @author Pedro Paulo
 *
 */
public class Music {
	/**
	 * The name of the music
	 */
	private String name;
	
	/**
	 * The artists of this music
	 */
	private String artist;
	
	/**
	 * The path of the music (absolute path)
	 */
	private String directory;

	/**
	 * Empty constructor
	 */
	public Music() {
		
	}
	
	/**
	 * Constructor with the directory
	 * @param directory the directory of the music
	 */
	public Music(String directory) {
		this.directory = directory;
	}
	
	/**
	 * Constructor with the name of the music and the directory
	 * @param name the name of the music
	 * @param directory the directory of the music
	 */
	public Music(String name, String directory) {
		this(directory);
		this.name = name;
	}
	
	/**
	 * Constructor with all atributes. Name of the music, artist and directory
	 * @param name the name of the music
	 * @param artist the artist of the music
	 * @param directory the directory of the music
	 */
	public Music(String name, String artist, String directory) {
		this(name, directory);
		this.artist = artist;
	}

	/**
	 * Get the music's name
	 * @return the music name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the music's name
	 * @param name the name of the music
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the music's artist
	 * @return the artist of the music
	 */
	public String getArtist() {
		return artist;
	}

	/**
	 * Set the music's artist
	 * @param artist the artist of the music
	 */
	public void setArtist(String artist) {
		this.artist = artist;
	}

	/**
	 * Get the music's directory (absolute path)
	 * @return the directory (absolute path) of the music
	 */
	public String getDirectory() {
		return directory;
	}

	/**
	 * Set the music's directory (absolute path)
	 * @param directory the directory (absolute path) of the music
	 */
	public void setDirectory(String directory) {
		this.directory = directory;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((artist == null) ? 0 : artist.hashCode());
		result = prime * result + ((directory == null) ? 0 : directory.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Music))
			return false;
		Music other = (Music) obj;
		if (artist == null) {
			if (other.artist != null)
				return false;
		} else if (!artist.equals(other.artist))
			return false;
		if (directory == null) {
			if (other.directory != null)
				return false;
		} else if (!directory.equals(other.directory))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
