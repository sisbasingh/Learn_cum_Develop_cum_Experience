package com.babu.design.mp3_player.beans;

import java.util.Date;
import java.util.UUID;

public class Song {
	String id;
	String singer;
	String artist;
	String writer;
	String movie;
	Date releaseDate;
	int size;  //in KB
	int length; //length in minutes
	
	public Song(String singer, String artist, String writer, String movie, Date releaseDate, int size, int length) {
		super();
		this.id = UUID.randomUUID().toString();
		this.singer = singer;
		this.artist = artist;
		this.writer = writer;
		this.movie = movie;
		this.releaseDate = releaseDate;
		this.size = size;
		this.length = length;
	}
	
	public String getId() {
		return id;
	}
	
	public int getLength() {
		return length;
	}

	@Override
	public String toString() {
		return "Song [id=" + id + ", singer=" + singer + ", artist=" + artist + ", writer=" + writer + ", movie="
				+ movie + ", releaseDate=" + releaseDate + ", size=" + size + ", length=" + length + "]";
	}
	
}
