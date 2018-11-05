package sk.gursky.paz1c.EntranceSystem.persistent;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class User {
	
	private Long id;
	private String chipId;
	private String name;
	private String passwordHash;
	private boolean active = true;
	private List<CardReader> cardReaders = new ArrayList<>();
	
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
	public boolean hasAccess(CardReader cr) {
		return cardReaders.contains(cr);
	}
	
	public String getPasswordHash() {
		return passwordHash;
	}
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", chipId=" + chipId + ", meno=" + name + ", active=" + active + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public static void main(String[] args) throws Throwable {
		String sol = new BigInteger(130, new SecureRandom()).toString(32);
		System.out.println("Sol: " + sol);
		String heslo = "heslo";
		String stringForHash = heslo + sol;
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		long start = System.nanoTime();
		md.update(stringForHash.getBytes());
		byte[] hash = md.digest();
		System.out.println("Cas pre SHA-256: " + (System.nanoTime() - start)/1000000.0 + " ms");
		String salt = BCrypt.gensalt();
		System.out.println(salt);
		start = System.nanoTime();
		String hashpw = BCrypt.hashpw(heslo, salt);
		System.out.println(hashpw);
		System.out.println("Cas pre BCrypt: " + (System.nanoTime() - start)/1000000.0 + " ms");
		boolean rovnake = BCrypt.checkpw(heslo, hashpw);
		System.out.println(rovnake);
		
	}
}
