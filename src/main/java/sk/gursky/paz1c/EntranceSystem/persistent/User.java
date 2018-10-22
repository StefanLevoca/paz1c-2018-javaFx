package sk.gursky.paz1c.EntranceSystem.persistent;

import java.util.List;

public class User {
	
	private Long id;
	private String chipId;
	private String name;
	private boolean active = true;
	private List<CardReader> cardReaders;
	
	public String getChipId() {
		return chipId;
	}
	public void setChipId(String chipId) {
		this.chipId = chipId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<CardReader> getCardReaders() {
		return cardReaders;
	}
	public void setCardReaders(List<CardReader> cardReaders) {
		this.cardReaders = cardReaders;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", chipId=" + chipId + ", meno=" + name + ", active=" + active + "]";
	}
}
