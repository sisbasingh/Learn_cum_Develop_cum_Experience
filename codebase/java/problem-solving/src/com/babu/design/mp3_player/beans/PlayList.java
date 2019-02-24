package com.babu.design.mp3_player.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayList {
	String id;
	List<Song> list;
	
	public PlayList() {
		super();
		this.id = UUID.randomUUID().toString();
		this.list = new ArrayList<>();
	}
	
	public void addSong(Song song) {
		list.add(song);
	}
	
	public void removeSong(Song song) {
		list.remove(song);
	}
	
	public int size() {
		return list.size();
	}
	
	public Song getSong(int index) {
		if(list.size() == 0) {
			throw new RuntimeException("PlayList is empty");
		}
		return list.get(index);
	}
}
