package com.babu.design.mp3_player;

import java.util.Date;
import java.util.Scanner;

import com.babu.design.mp3_player.beans.PlayList;
import com.babu.design.mp3_player.beans.Song;

public class MP3Player {
	String name;
	String version;

	int volume;
	ScreenSize scsize;

	Song currentSong;
	int curSongIndex;
	int currSongPlayedLenght;
	Thread playerThread;
	PlayList playList;

	public MP3Player(String name, String version, PlayList pl) {
		this.name = name;
		this.version = version;
		this.playList = pl;
		curSongIndex = pl.size() > 0 ? 0 : -1;
		currentSong = playList.getSong(curSongIndex);
		currSongPlayedLenght = 0;
	}

	public void play(boolean doPlay) {
		synchronized (this) {
			while (doPlay) {
				try {
					playerThread.sleep(1000);
					currSongPlayedLenght += 1;
					if (currSongPlayedLenght == playList.getSong(curSongIndex).getLength() * 60) {
						// Current song has been completed start the next song
						curSongIndex = (curSongIndex + 1) % playList.size();
						doPlay = curSongIndex == 0 ? false : true;
						currentSong = playList.getSong(curSongIndex);
						currSongPlayedLenght = 0;
					}
					/**
					 * Do update UI of player and tracks
					 */
					updatePlayerUI();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void play(Song song) {
		// Playing after pause
		if (playerThread != null) {
			if (currentSong != null && song.getId() != currentSong.getId()) {
				currentSong = song;
				currSongPlayedLenght = 0;
			}
			playerThread.notify();
		} else {
			playerThread = new Thread(new Runnable() {

				@Override
				public void run() {
					play(true);
				}
			});
		}
		/**
		 * Start to play
		 */
		playerThread.start();
	}

	public void playPrevious() {
		curSongIndex = (curSongIndex - 1 + playList.size()) % playList.size();
		play(playList.getSong(curSongIndex));
	}

	public void playNext() {
		curSongIndex = (curSongIndex + 1) % playList.size();
		play(playList.getSong(curSongIndex));
	}

	public void updatePlayerUI() {
		System.out.println("Currently playing song: " + currentSong + " at length: " + currSongPlayedLenght);
	}

	public void pause() throws InterruptedException {
		playerThread.wait();
	}

	public void close() {
		if (playerThread.isInterrupted()) {
			return;
		}
		playerThread.interrupt();
	}

	public void increaseVolume() {
		if (volume < Integer.MAX_VALUE) {
			volume++;
		}
	}

	public void decreaseVolume() {
		if (volume > 0) {
			volume--;
		}
	}

	public enum ScreenSize {
		MINIMIZE(0), CUSTOM(1), FULLSCREEN(3);

		int value;

		private ScreenSize(int v) {
			value = v;
		}
	}

	/**
	 * Driver method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Song song = new Song("Arijit", "Anu Malik", "Javed", "Hello world", new Date(), 6000, 1);
		PlayList pl = new PlayList();
		pl.addSong(song);
		MP3Player mp3Player = new MP3Player("WMP", "5.41", pl);

		Scanner in = new Scanner(System.in);
		while (true) {
			mp3Player.printHelp();
			int choice = Integer.parseInt(in.nextLine());
			switch (choice) {
			case 1:
			case 4:
				mp3Player.playNext();
				break;
			case 2:
				try {
					mp3Player.pause();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				break;
			case 3:
				mp3Player.playPrevious();
				break;
			case 5:
				mp3Player.close();
				return;
			default:
				System.out.println("Invalid Choice");
				break;
			}
		}
	}

	public void printHelp() {
		System.out.println("Please enter your choice: \n1.Play\n2.Pause\n3.Prev\n4.Next\n5.Stop\n");
	}
}
